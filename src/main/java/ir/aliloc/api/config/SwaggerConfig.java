package ir.aliloc.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
//                .paths(regex("/api.*"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .securitySchemes(Arrays.asList(apiKey()));
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Amn Moj (Motahari)",
                "سرویسهای جامع مطهری",
                "1.0",
                "Terms of service",
                new Contact("John Thompson", "http://srv1.motahari.ir:8081/motahari", "alikarimipour157@gmail.com"),
                "amn moj license",
                "https://www.amnmoj.com/licenses/LICENSE-2.0");
        return apiInfo;
    }

    private ApiKey apiKey() {
        return new ApiKey("authkey", "token", "header");
    }
}
