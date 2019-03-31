package ampos.restaurant.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import java.util.Arrays;

@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.select()
					.apis(RequestHandlerSelectors.any()).paths(studentsPaths())
					.paths(PathSelectors.any())
					.build().
				securitySchemes(Arrays.asList(basicAuth()));
	}

	private Predicate<String> studentsPaths() {
		return Predicates.or(
				regex("/api/menu.*"),
				regex("/api/order.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Restaurant API")
				.description("The Restaurant API ")
				.version("1.0").build();
	}

	private SecurityScheme basicAuth() {
		return new BasicAuth("Basic Authentication");
	}
}
