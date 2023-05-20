package com.sixdee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@ComponentScan(basePackages = { "com.sixdee" })

public class GameEventsApplication {

	/*
	 * protected SpringApplicationBuilder configure(SpringApplicationBuilder
	 * builder) { return builder.sources(GameEventsApplication.class); }
	 */

	public static void main(String[] args) {
		SpringApplication.run(GameEventsApplication.class, args);
	}

	

	/*
	 * public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurerAdapter() {
	 * 
	 * 
	 * 
	 * public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/loyaltyMangamement").allowedOrigins("*"); } }; }
	 */
	/*
	 * @Bean public DispatcherServletRegistrationBean
	 * dispatcherServletRegistrationBean() { return new
	 * DispatcherServletRegistrationBean(dispatcherServlet(), "/"); }
	 * 
	 * @Bean public DispatcherServlet dispatcherServlet() { return new
	 * DispatcherServlet(); }
	 */
}
