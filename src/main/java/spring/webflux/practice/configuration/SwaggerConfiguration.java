package spring.webflux.practice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author Ahsuoy Yekoraf
 * @since 9/26/21
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .enable(true)
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction(
            @Value("${reactive.server.servlet.context-path}") String contextPath) {
        return route(GET("/"), req ->
                ServerResponse.temporaryRedirect(URI.create(contextPath + "/swagger-ui/"))
                        .build());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Webflux Practice API")
                .description("Spring Webflux Practice API")
                .version("1.0.0")
                .build();
    }
}
