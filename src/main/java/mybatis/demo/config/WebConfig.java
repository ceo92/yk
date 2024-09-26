package mybatis.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.util.List;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import mybatis.demo.interceptor.LoginInterceptor;
import mybatis.demo.login.LoginArgumentResolver;

@Configuration
//@EnableWebSecurity , 이 어노테이션 쓰면 시큐리티 적용하겠다는 뜻
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new LoginArgumentResolver());
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/auth/**")
        .excludePathPatterns("/images/**")
        .excludePathPatterns("/css/**")
        .excludePathPatterns("/js/**")
        .excludePathPatterns("/test/**")
        .excludePathPatterns("/error/**")
        .order(1);
  }


  /**
   * Swagger
   */

  @Bean
  public GroupedOpenApi restApi(){
    return GroupedOpenApi.builder().pathsToMatch("/api/**").group("REST API").build();
  }





  @Bean
  public OpenAPI openAPI(){
    return new OpenAPI().info(new Info().title("SpringDoc SwaggerUI example")
            .description("Test SwaggerUI application")
            .version("v0.0.1"));
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
