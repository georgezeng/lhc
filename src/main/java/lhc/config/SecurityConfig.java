package lhc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService detailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
		http.rememberMe().userDetailsService(detailService).alwaysRemember(true);
		http.csrf().disable();
		http.authorizeRequests().anyRequest().authenticated();
	}

	@Bean
	public UserDetailsService userDetailsServiceImpl() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("138lh").roles("USER").build());
		return manager;
	}
}