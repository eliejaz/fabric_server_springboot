package com.medicalRecord.hyperLedgerServer.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.medicalRecord.hyperLedgerServer.Security.AuthEntryPointJwt;
import com.medicalRecord.hyperLedgerServer.Security.AuthTokenFilter;
import com.medicalRecord.hyperLedgerServer.Security.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/v2/api-docs/**", "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/**",
			"/swagger-ui.html", "/swagger-ui.html/**", "/webjars/**", "/favicon.ico",
			// -- Swagger UI v3 (OpenAPI)
			"/swagger-ui-custom.html", "/v3/api-docs/**", "/v3/api-docs", "/swagger-ui/**", "/api/swagger-ui.html" };

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic().disable()
			.cors().and()
			.csrf().disable()
		 	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		    .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
//				.antMatchers(HttpMethod.POST, "/test_user").hasRole("USER")
	      		.authorizeRequests()
				.antMatchers(AUTH_WHITELIST).permitAll()
				//.antMatchers("").hasIpAddress()
				//.anyRequest().authenticated();
				.anyRequest().permitAll();

	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

		    
		 
	
	 
}
