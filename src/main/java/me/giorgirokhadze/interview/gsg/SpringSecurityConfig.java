package me.giorgirokhadze.interview.gsg;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("/user").hasRole("USER")
				.antMatchers("/user/update").hasRole("USER")
				.and()
				.csrf().disable()
				.formLogin().disable();

	}
}
