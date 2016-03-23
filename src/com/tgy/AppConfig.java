package com.tgy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/hailunhuafei/**").addResourceLocations(
				"/hailunhuafei/**");
		
		registry.addResourceHandler("/javascripts/**").addResourceLocations(
				"/javascripts/**");
		registry.addResourceHandler("/myjs/**")
				.addResourceLocations("/myjs/**");
		registry.addResourceHandler("/stylesheets/**").addResourceLocations(
				"/stylesheets/**");
		registry.addResourceHandler("/part/**").addResourceLocations(
				"/part/**");
		registry.addResourceHandler("/adadm/**").addResourceLocations(
				"/adadm/**");
		registry.addResourceHandler("/op/**").addResourceLocations(
				"/op/**");
		registry.addResourceHandler("/window/**").addResourceLocations(
				"/window/**");
		registry.addResourceHandler("/fonts/**").addResourceLocations(
				"/fonts/**");
		registry.addResourceHandler("/images/**").addResourceLocations(
				"/images/**");
		registry.addResourceHandler("/html/**").addResourceLocations(
				"/html/**");
		registry.addResourceHandler("/che/**").addResourceLocations(
				"/che/**");

		registry.addResourceHandler("/ext/**").addResourceLocations(
				"/ext/**");
		registry.addResourceHandler("/app/**").addResourceLocations(
				"/app/**");
		registry.addResourceHandler("/download/**").addResourceLocations(
				"/download/**");
		registry.addResourceHandler("/m/**").addResourceLocations(
				"/m/**");
		
//		registry.addResourceHandler("/folder/favicon").addResourceLocations(
//				"/fonts/**");
		
	}
}