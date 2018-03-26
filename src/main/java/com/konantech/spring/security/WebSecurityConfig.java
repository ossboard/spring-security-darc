package com.konantech.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DarcAuthenticationProvider darcAuthenticationProvider;
	@Autowired
	private DarcLogoutSuccessHandler darcLogoutSuccessHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/docs/**", "/");
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(this.authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.headers().disable()
				.csrf().disable()
				.authorizeRequests()

				// web
				.antMatchers("/user/**").permitAll()

				// server
				.antMatchers("/v2/login").permitAll()
				.antMatchers("/v2/api-docs").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/swagger*/**").permitAll()
				.antMatchers("/docs/**").permitAll()

				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/user/login")
				.failureUrl("/user/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/", true)
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessHandler(darcLogoutSuccessHandler)
				.logoutSuccessUrl("/user/login?logout");

		http.httpBasic().realmName("KONAN_REALM").authenticationEntryPoint(getBasicAuthEntryPoint());
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public DarcBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new DarcBasicAuthenticationEntryPoint();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(darcAuthenticationProvider);
    }

}
