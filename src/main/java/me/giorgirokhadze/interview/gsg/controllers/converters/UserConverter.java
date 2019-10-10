package me.giorgirokhadze.interview.gsg.controllers.converters;

import me.giorgirokhadze.interview.gsg.model.User;
import me.giorgirokhadze.interview.gsg.model.Video;
import me.giorgirokhadze.interview.gsg.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter implements Converter<UserEntity, User> {

	private final VideoConverter videoConverter;

	public UserConverter(VideoConverter videoConverter) {
		this.videoConverter = videoConverter;
	}

	@Override
	public User convert(UserEntity originalValue) {
		final User user = new User();
		user.setUsername(originalValue.getUsername());
		user.setRegionCode(originalValue.getRegionCode());
		user.setScheduledMinutes(originalValue.getScheduledMinutes());
		List<Video> videos = new ArrayList<>();
		originalValue.getVideos().forEach(video -> videos.add(videoConverter.convert(video)));
		user.setVideos(videos);
		return user;
	}
}
