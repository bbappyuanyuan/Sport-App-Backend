package cn.edu.fudan.sport.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@PropertySource("classpath:swagger-config.properties")
@EnableSwagger2
public class ApplicationSwaggerConfig {

}
