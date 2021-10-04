package com.rdbms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	public SwaggerConfiguration() {
	}

	@Bean
	public Docket swaggerSetting() {
		// 設定 Swagger UI 預設使用哪個版本作為 openAPI 規則
		// DocumentationType.SWAGGER_12：swagger 1.2
		// DocumentationType.SWAGGER_2：swagger 2.0
		// DocumentationType.OAS_30：openApi 3.0
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo()).select()
				// 指定參照的套件位置 (不用的話預設會多出一個 swagger ui 範例的 error controller )
				.apis( RequestHandlerSelectors.basePackage("com.rdbms") )
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Restful Service API Documentation")
				.description("Learning Spring Boot , let's goooooooooooo")
				.version("ver - 0.12345")
				.build();
	}
}
