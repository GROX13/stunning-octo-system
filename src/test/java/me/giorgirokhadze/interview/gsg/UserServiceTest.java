package me.giorgirokhadze.interview.gsg;

import com.google.api.services.youtube.YouTube;
import me.giorgirokhadze.interview.gsg.controllers.model.UserData;
import me.giorgirokhadze.interview.gsg.persistence.UserRepository;
import me.giorgirokhadze.interview.gsg.persistence.entities.UserEntity;
import me.giorgirokhadze.interview.gsg.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Collections;

@SpringBootTest
class UserServiceTest {

	@MockBean
	private YouTube youTube;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void savesYoutubeDataCorrectly() throws IOException {
		UserEntity user = new UserEntity();
		user.setUsername("test");
		user.setEncodedPassword("test");
		user.setRegionCode("ge");
		user.setScheduledMinutes(10);

		user = userRepository.saveAndFlush(user);

		youTube = Mockito.mock(YouTube.class);

		YouTube.Videos videos = Mockito.mock(YouTube.Videos.class);

		Mockito.when(videos.list("id")).thenReturn(null);
		Mockito.when(youTube.videos()).thenReturn(videos);

		SecurityContextHolder
				.getContext()
				.setAuthentication(
						new UsernamePasswordAuthenticationToken(
								"test",
								"test",
								Collections.singletonList(
										new SimpleGrantedAuthority("USER")
								))
				);

		UserData userData = new UserData();
		userData.setRegionCode("us");
		userData.setScheduledMinutes(1);

		userService.updateUser(userData);
	}
}
