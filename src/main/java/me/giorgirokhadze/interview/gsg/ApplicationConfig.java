package me.giorgirokhadze.interview.gsg;

import com.google.api.services.youtube.YouTube;
import me.giorgirokhadze.interview.gsg.persistence.UserRepository;
import me.giorgirokhadze.interview.gsg.utils.YoutubeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.security.GeneralSecurityException;

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
	public YouTube youTube() throws GeneralSecurityException, IOException {
		return YoutubeUtils.getService();
	}

	@Bean
	@Profile("prod")
	public YouTube youTubeOAuth() throws GeneralSecurityException, IOException {
		return YoutubeUtils.getServiceOAuth();
	}

	@Bean
	public YoutubeUtils.ApiKeyProvider apiKeyProvider() throws IOException {
		return YoutubeUtils.getApiKeyProvider();
	}

	@Bean
	public YoutubeUtils.ServiceAccountKeyProvider serviceAccountKeyProvider() throws IOException {
		return YoutubeUtils.getServiceAccountKeyProvider();
	}

	@Bean
	@Profile("dev")
	public DataInitializer dataInitializer(
			PasswordEncoder passwordEncoder,
			UserRepository userRepository
	) {
		return new DataInitializer(passwordEncoder, userRepository);
	}
}
