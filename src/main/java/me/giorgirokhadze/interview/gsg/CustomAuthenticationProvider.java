package me.giorgirokhadze.interview.gsg;

import me.giorgirokhadze.interview.gsg.persistence.UserRepository;
import me.giorgirokhadze.interview.gsg.persistence.entities.UserEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public CustomAuthenticationProvider(
			PasswordEncoder passwordEncoder,
			UserRepository userRepository
	) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = authentication.getName();
		final CharSequence password = authentication.getCredentials().toString();

		UserEntity user = userRepository.findByUsername(username);

		if (user != null && passwordEncoder.matches(password, user.getEncodedPassword())) {
			return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
		}
		throw new BadCredentialsException(String.format("Authentication failed for user: %s", username));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
