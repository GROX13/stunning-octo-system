package me.giorgirokhadze.interview.gsg;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.csrf().disable()
				.formLogin().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user**").hasAuthority("USER")
				.antMatchers(HttpMethod.POST, "/logout").permitAll()
				.antMatchers(HttpMethod.POST, "/user/register").permitAll();
	}
}
