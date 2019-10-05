package me.giorgirokhadze.interview.gsg.services;

import me.giorgirokhadze.interview.gsg.controllers.converters.UserConverter;
import me.giorgirokhadze.interview.gsg.controllers.model.RegistrationData;
import me.giorgirokhadze.interview.gsg.controllers.model.UserData;
import me.giorgirokhadze.interview.gsg.model.User;
import me.giorgirokhadze.interview.gsg.persistence.UserRepository;
import me.giorgirokhadze.interview.gsg.persistence.entities.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {

	private final UserRepository userRepository;

	private final UserConverter userConverter;

	private final PasswordEncoder passwordEncoder;

	public AccountService(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userConverter = userConverter;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public void updateUser(UserData userData) {
		UserEntity user = userRepository.findByUsername(getLoggedInUsername());
		user.setScheduledMinutes(userData.getScheduledMinutes());
		user.setRegionCode(userData.getRegionCode());

		userRepository.saveAndFlush(user);
	}

	@Transactional
	public void registerUser(RegistrationData registrationData) {
		UserEntity user = new UserEntity();
		user.setUsername(registrationData.getUsername());
		user.setEncodedPassword(passwordEncoder.encode(registrationData.getPassword()));
		user.setRegionCode(registrationData.getRegionCode());
		user.setScheduledMinutes(registrationData.getScheduledMinutes());

		userRepository.saveAndFlush(user);
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
}
