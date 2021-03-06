package me.giorgirokhadze.interview.gsg;

import me.giorgirokhadze.interview.gsg.persistence.UserRepository;
import me.giorgirokhadze.interview.gsg.persistence.entities.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DataInitializer implements CommandLineRunner {

	private static final String USERNAME = "giorgi";
	private static final String PASSWORD = "pass123";
	private static final String REGION_CODE = "ge";

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public DataInitializer(
			PasswordEncoder passwordEncoder,
			UserRepository userRepository
	) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) {
		UserEntity user = userRepository.findByUsername(DataInitializer.USERNAME);

		if (user == null) {
			UserEntity userEntity = new UserEntity();
			userEntity.setUsername(USERNAME);
			userEntity.setRegionCode(REGION_CODE);
			userEntity.setScheduledMinutes(15);
			userEntity.setEncodedPassword(passwordEncoder.encode(PASSWORD));
			userRepository.saveAndFlush(userEntity);
		}
	}
}
