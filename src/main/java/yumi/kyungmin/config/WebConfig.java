package yumi.kyungmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import yumi.kyungmin.domain.Member;

@Configuration
//@EnableWebSecurity
public class WebConfig{

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests(authz -> authz.anyRequest().authenticated()).formLogin(
        Customizer.withDefaults());

    return httpSecurity.build();
  }
  @Bean //PasswordEncoder 빈 등록 , 개발자가 어떤 암호화 구현체 쓸지에 따라 개발자가 수동으로 빈 등록 해줘야됨 , BCrypt도 있고 다양하게 있음
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }


}
