package com.sms.msgsend.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger 启动类
 * @author wend
 */
@Configuration
@EnableSwagger2
@EnableAsync
@ComponentScan(basePackages = {"com.sms.msgsend.controller"})
public class SwaggerConfigurer extends WebMvcConfigurationSupport {
	private static final String ACCESS_TOKEN = "Access-Token";

	@Bean
	public Docket createRestApi() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name(ACCESS_TOKEN)
				.description(ACCESS_TOKEN)
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build();
		pars.add(tokenPar.build());

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 需要扫描的包路径
				.apis(RequestHandlerSelectors.basePackage("com.sms.msgsend.controller"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts())
				.globalOperationParameters(pars);
	}

	private List<ApiKey> securitySchemes() {
		List<ApiKey> result = new ArrayList<>();
		result.add(new ApiKey(ACCESS_TOKEN, ACCESS_TOKEN, "header"));
		return result;
	}

	private List<SecurityContext> securityContexts() {
		List<SecurityContext> result = new ArrayList<>();
		result.add(SecurityContext.builder().securityReferences(defaultAuth())
				           .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
		return result;
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> result = new ArrayList<>();
		result.add(new SecurityReference("Authorization", authorizationScopes));
		return result;
	}

	/**
	 * 构建api文档的详细信息函数
	 *
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
		Contact contact = new Contact("Wend", "http://xxx.xxx.com/", "827820318@qq.com");
		return new ApiInfoBuilder()
				.title("WendSwagger")
				.contact(contact)
				.version("1.0")
				.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
