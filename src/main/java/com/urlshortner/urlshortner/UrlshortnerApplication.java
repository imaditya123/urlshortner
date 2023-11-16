package com.urlshortner.urlshortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
//@EnableWebMvc
public class UrlshortnerApplication{

	public static void main(String[] args) {
		SpringApplication.run(UrlshortnerApplication.class, args);
	}
	
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	    registry.addResourceHandler("swagger-ui.html")
//	      .addResourceLocations("classpath:/META-INF/resources/");
//
//	    registry.addResourceHandler("/webjars/**")
//	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}


}
