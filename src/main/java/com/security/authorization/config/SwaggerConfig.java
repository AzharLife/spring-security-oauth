package com.security.authorization.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		List<springfox.documentation.service.Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("Authorization")
                .description("Access token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build());
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.security.scf.authorization.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo())
				.globalOperationParameters(parameters);
	}
	
	public ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("security SCF",
				"Supply Chain Factory for security", "1.0", "Azhar",
				"azhar", "Azhar", "Azhar");
		return apiInfo;
	}
}
