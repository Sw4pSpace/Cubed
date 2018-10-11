package net.sw4pspace.cubedwebservices.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.any;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket produceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.cubed.webservices.controller"))
                .paths(any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Cubed WebServices",
                "WebServices for the Cubed Minecraft Server",
                "1.0",
                "Terms of service",
                new Contact("@sw4psapce", "https://github.com/sw4psapce", ""),
                "",
                "",
                Collections.emptyList());
        return apiInfo;
    }

}
