package com.mjv.gamequiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GamequizApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamequizApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200/", "http://localhost:8080/swagger-ui/index.html")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
//                        .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization")
//                        .allowCredentials(true);
			}
		};
	}

}
