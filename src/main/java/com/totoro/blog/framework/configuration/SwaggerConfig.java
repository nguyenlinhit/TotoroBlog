package com.totoro.blog.framework.configuration;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @className: SwaggerConfig
 * @description: Description here!!!
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final TotoroBlogConfig totoroBlogConfig;

    public SwaggerConfig(TotoroBlogConfig totoroBlogConfig) {
        this.totoroBlogConfig = totoroBlogConfig;
    }

    /**
     * Create Swagger API.
     */
    public Docket createResApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/dev-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     *
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    /**
     *
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEveryThing");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        List<SecurityReference> securityReferences = new ArrayList<>();

        authorizationScopes[0] = authorizationScope;
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));

        return securityReferences;
    }

    /**
     *
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));

        return apiKeyList;
    }

    /**
     *
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Title: TotoroBlog background management system")
                .description("Open source code: https://github.com/nguyenlinhit/TotoroBlog")
                .version("Version: " + totoroBlogConfig.getVersion())
                .build();
    }
}
