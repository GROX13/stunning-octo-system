package me.giorgirokhadze.interview.gsg.controllers.converters;

import me.giorgirokhadze.interview.gsg.model.User;
import me.giorgirokhadze.interview.gsg.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserConverter implements Converter<UserEntity, User> {

	@Override
	public User convert(UserEntity originalValue) {
		final User user = new User();
		user.setUsername(originalValue.getUsername());
		user.setRegionCode(originalValue.getRegionCode());
		user.setScheduledMinutes(originalValue.getScheduledMinutes());
		user.setVideos(Collections.emptyList());
		return user;
	}
}
