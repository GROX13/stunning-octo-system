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
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/user/register").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic();

//		http
//				.httpBasic()
//				.and()
//				.formLogin().disable()
//				.authorizeRequests()
//				.antMatchers(HttpMethod.OPTIONS, "/user").hasAuthority("USER")
//				.antMatchers(HttpMethod.OPTIONS, "/user/**").hasAuthority("USER")
//				.antMatchers(HttpMethod.OPTIONS, "/logout").permitAll()
//				.antMatchers(HttpMethod.OPTIONS, "/user/register").permitAll();
	}
}
