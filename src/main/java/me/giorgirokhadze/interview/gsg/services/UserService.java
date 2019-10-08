package me.giorgirokhadze.interview.gsg.services;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import me.giorgirokhadze.interview.gsg.controllers.converters.UserConverter;
import me.giorgirokhadze.interview.gsg.controllers.model.RegistrationData;
import me.giorgirokhadze.interview.gsg.controllers.model.UserData;
import me.giorgirokhadze.interview.gsg.model.User;
import me.giorgirokhadze.interview.gsg.persistence.CommentRepository;
import me.giorgirokhadze.interview.gsg.persistence.UserRepository;
import me.giorgirokhadze.interview.gsg.persistence.VideoRepository;
import me.giorgirokhadze.interview.gsg.persistence.entities.CommentEntity;
import me.giorgirokhadze.interview.gsg.persistence.entities.UserEntity;
import me.giorgirokhadze.interview.gsg.persistence.entities.VideoEntity;
import me.giorgirokhadze.interview.gsg.utils.YoutubeUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class UserService {

	private final YouTube youtubeService;

	private final UserRepository userRepository;

	private final VideoRepository videoRepository;

	private final CommentRepository commentRepository;

	private final UserConverter userConverter;

	private final PasswordEncoder passwordEncoder;

	private final SchedulingService schedulingService;

	private final YoutubeUtils.ApiKeyProvider apiKeyProvider;

	public UserService(
			YouTube youtubeService,
			UserRepository userRepository,
			VideoRepository videoRepository,
			CommentRepository commentRepository,
			UserConverter userConverter,
			PasswordEncoder passwordEncoder,
			SchedulingService schedulingService,
			YoutubeUtils.ApiKeyProvider apiKeyProvider
	) {
		this.youtubeService = youtubeService;
		this.userRepository = userRepository;
		this.videoRepository = videoRepository;
		this.commentRepository = commentRepository;
		this.userConverter = userConverter;
		this.passwordEncoder = passwordEncoder;
		this.schedulingService = schedulingService;
		this.apiKeyProvider = apiKeyProvider;
	}

	@Transactional
	public void updateUser(UserData userData) {
		UserEntity user = userRepository.findByUsername(getLoggedInUsername());
		user.setScheduledMinutes(userData.getScheduledMinutes());
		user.setRegionCode(userData.getRegionCode());

		final UserEntity savedEntity = userRepository.saveAndFlush(user);

		schedulingService.schedule(
				getRunnable(savedEntity),
				savedEntity.getScheduledMinutes(),
				savedEntity.getId()
		);
	}

	@Transactional
	public void registerUser(RegistrationData registrationData) {
		UserEntity user = new UserEntity();
		user.setUsername(registrationData.getUsername());
		user.setEncodedPassword(passwordEncoder.encode(registrationData.getPassword()));
		user.setRegionCode(registrationData.getRegionCode());
		user.setScheduledMinutes(registrationData.getScheduledMinutes());

		final UserEntity savedEntity = userRepository.saveAndFlush(user);

		schedulingService.schedule(
				getRunnable(savedEntity),
				savedEntity.getScheduledMinutes(),
				savedEntity.getId()
		);
	}

	public User getLoggedInUser() {
		return userConverter.convert(userRepository.findByUsername(getLoggedInUsername()));
	}

	private String getLoggedInUsername() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	private Runnable getRunnable(UserEntity userEntity) {
		return () -> {
			try {
				YouTube.Videos.List request = youtubeService.videos().list("id");

				VideoListResponse response = request
						.setChart("mostPopular")
						.setMaxResults(1L)
						.setRegionCode(userEntity.getRegionCode())
						.execute();

				Video video = response.getItems().get(0);

				VideoEntity videoEntity = new VideoEntity();
				videoEntity.setVideoId(video.getId());
				videoEntity.setUser(userEntity);
				videoEntity = videoRepository.saveAndFlush(videoEntity);

				YouTube.CommentThreads.List commentThreadsRequest = youtubeService.commentThreads()
						.list("id");
				CommentThreadListResponse commentThreadsResponse = commentThreadsRequest.setKey(apiKeyProvider.getKey())
						.setMaxResults(1L)
						.setOrder("relevance")
						.setVideoId(video.getId())
						.execute();

				CommentThread commentThread = commentThreadsResponse.getItems().get(0);

				CommentEntity commentEntity = new CommentEntity();
				commentEntity.setCommentId(commentThread.getId());
				commentEntity.setVideo(videoEntity);
				commentRepository.saveAndFlush(commentEntity);
			} catch (IOException e) {
				throw new RuntimeException(e.getLocalizedMessage());
			}
		};
	}
}
