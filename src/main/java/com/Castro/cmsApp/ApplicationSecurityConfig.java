package com.Castro.cmsApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {

    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authz) -> authz
            .anyRequest().permitAll()
        // .requestMatchers("/login", "/resources/**", "/css/**", "/fonts/**",
        // "/img/**").permitAll()
        // .requestMatchers("/register", "/resources/**", "/css/**", "/fonts/**",
        // "/img/**", "/js/**").permitAll()
        // .requestMatchers("/users/addNew").permitAll()
        // .requestMatchers("/security/user/Edit/**").hasAuthority("ADMIN")
        // .anyRequest().authenticated()

        )
        .httpBasic(withDefaults());
    return http.build();
  }

  // @Override
  // protected void configure(HttpSecurity http) throws Exception {
  // http
  // .csrf().disable()
  // .authorizeRequests()
  // .anyRequest().authenticated()
  // .and()
  // .formLogin()
  // .loginPage("/login").permitAll()
  // .defaultSuccessUrl("/index")
  // .and()
  // .logout().invalidateHttpSession(true)
  // .clearAuthentication(true)
  // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
  // .logoutSuccessUrl("/login").permitAll()
  // .and()
  // .exceptionHandling().accessDeniedPage("/accessDenied")
  // ;
  // }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

    provider.setUserDetailsService(userDetailsService);

    provider.setPasswordEncoder(bCryptPasswordEncoder());
    return provider;
  }

}
