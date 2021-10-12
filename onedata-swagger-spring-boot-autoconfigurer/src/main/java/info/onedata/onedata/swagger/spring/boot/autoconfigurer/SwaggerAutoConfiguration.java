package info.onedata.onedata.swagger.spring.boot.autoconfigurer;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.sun.corba.se.spi.ior.ObjectId;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
@EnableKnife4j
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(value = "openapi.swagger.enabled", havingValue = "true")
public class SwaggerAutoConfiguration {

    @Bean
    public Docket createRestApi(SwaggerProperties swaggerProperties, ApiInfo apiInfo) throws ClassNotFoundException {

        if (StringUtils.isEmpty(swaggerProperties.getPackages())) {
            throw new RuntimeException("缺失参数， openapi.swagger.packages");
        }
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getPackages()))
                .paths(PathSelectors.any()).build();
        String[] ignoreClasses = swaggerProperties.getIgnoreClasses();

        if (ignoreClasses != null) {
            docket.ignoredParameterTypes(Arrays.stream(ignoreClasses).map(item -> {
                try {
                    return Class.forName(item);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }).toArray(Class[]::new));
        }
        return docket.directModelSubstitute(ObjectId.class, String.class);
    }

    @Bean
    public ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        return (new ApiInfoBuilder()).title(swaggerProperties.getTitle()).version(swaggerProperties.getVersion())
                .description(swaggerProperties.getDescription()).contact(swaggerProperties.getContact()).build();
    }
}

