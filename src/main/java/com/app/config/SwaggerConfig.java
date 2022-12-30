package com.app.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.app"))
				//.paths(PathSelectors.any())
				.paths(PathSelectors.regex("/v1/api.*"))
				.build()
				.pathMapping("/")
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("STUDENT APP", 
				"SAMPLE APP", 
				"3.2GA", 
				"http://sample.com", 
				new Contact("RAGHU", "http://github.com/javabyraghu", "javabyraghu@gmail.com"), 
				"APACHE", 
				"http://apache.com", 
				Collections.emptyList());
	}
}
