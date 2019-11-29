package com.michups;

import com.michups.model.CurrentUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication   // <1>
public class LoginRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginRestApplication.class, args); // <2>
    }

//    @Bean
//    @Scope(value = WebApplicationContext.SCOPE_SESSION)
//    public CurrentUser currentUser() {
//        return new CurrentUser();
//    }
//
//    @Bean
//    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//        config.setAllowedMethods(Collections.singletonList("*"));
//        config.setAllowedHeaders(Collections.singletonList("*"));
//        config.setMaxAge(3600L);
//        config.setAllowCredentials(true);
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }

//
//  @Bean
//  public WebMvcConfigurer corsConfigurer() {
//    return new WebMvcConfigurerAdapter() {
//      @Override
//      public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
//      }
//    };
//  }
}
