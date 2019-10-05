package me.giorgirokhadze.interview.gsg;

import me.giorgirokhadze.interview.gsg.persistence.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CustomAuthenticationProvider authProvider(
			PasswordEncoder passwordEncoder,
			UserRepository userRepository
	) {
		return new CustomAuthenticationProvider(passwordEncoder, userRepository);
	}

	@Bean
	public DataInitializer dataInitializer(
			PasswordEncoder passwordEncoder,
			UserRepository userRepository
	) {
		return new DataInitializer(passwordEncoder, userRepository);
	}
}
