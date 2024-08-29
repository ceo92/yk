package mybatis.demo.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import mybatis.demo.interceptor.LoginInterceptor;
import mybatis.demo.login.LoginArgumentResolver;

@Configuration
//@EnableWebSecurity
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new LoginArgumentResolver());
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor())
        .addPathPatterns("/items/**")
        .addPathPatterns("/members")
        .addPathPatterns("/member")
        .order(1);
  }

  /*@Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authz -> authz.anyRequest().authenticated()).formLogin(
            Customizer.withDefaults());

        return httpSecurity.build();
      }*/
  @Bean //PasswordEncoder 빈 등록 , 개발자가 어떤 암호화 구현체 쓸지에 따라 개발자가 수동으로 빈 등록 해줘야됨 , BCrypt도 있고 다양하게 있음
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }




}
