package es.prices.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Config for swagger
 */
@Configuration
@EnableSwagger2
@ComponentScan
public class SwaggerConfig {
    /**
     * Create Swagger Api configuration
     *
     * @return Swagger Docket
     */
    @Bean
    public Docket documentApi() {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(apiKey());
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                select().
                apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).
                paths(PathSelectors.any()).build().
                pathMapping("/").
                genericModelSubstitutes(ResponseEntity.class).
                genericModelSubstitutes(Optional.class).
                securitySchemes(securitySchemes).
                useDefaultResponseMessages(false).
                forCodeGeneration(true);
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

    /**
     * Generate Api Info
     *
     * @return Swagger API Info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Prices Api").description("Prices REST API").build();
    }

}
