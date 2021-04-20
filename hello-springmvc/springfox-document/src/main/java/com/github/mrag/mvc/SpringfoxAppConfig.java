package com.github.mrag.mvc;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.github.mrag.mvc.common.CommonFilter;
import com.github.mrag.mvc.common.CommonInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Gmw
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SpringfoxAppConfig implements WebMvcConfigurer {

    private final Jackson2ObjectMapperBuilder objectMapperBuilder;

    public SpringfoxAppConfig(Jackson2ObjectMapperBuilder objectMapperBuilder) {
        this.objectMapperBuilder = objectMapperBuilder;
    }

    @Bean(name = "objectMapper")
    @Primary
    ObjectMapper createObjectMapper() {
        return objectMapperBuilder
                .createXmlMapper(false)
                .modules(newJavaTimeModule())
                .build();
    }

    @Bean(name = "xmlMapper")
    ObjectMapper createXmlMapper() {
        return objectMapperBuilder
                .createXmlMapper(true)
                .modules(newJavaTimeModule())
                .build();
    }

    @Bean
    FilterRegistrationBean<CommonFilter> createCommonFilter() {
        FilterRegistrationBean<CommonFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new CommonFilter());
        filter.setOrder(1);
        filter.addUrlPatterns("/*");
        return filter;
    }

    private Module newJavaTimeModule() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new JavaTimeModule()
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(createObjectMapper()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(createXmlMapper()));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Resource
    CommonInterceptor commonInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor);
    }

    @Bean
    Docket createRestApi() {
        ObjectMapper objectMapper = createObjectMapper();
        System.out.println(objectMapper);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.mrag.mvc.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springfox Swagger Document")
                .description("springfox-swagger-2.9.2 demo")
                .contact(new Contact("Mrag", "mrag.io", "mrag@gmail.com"))
                .version("1.0-RELEASE")
                .build();
    }
}
