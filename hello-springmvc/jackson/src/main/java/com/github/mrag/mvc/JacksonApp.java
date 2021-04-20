package com.github.mrag.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class JacksonApp implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(JacksonApp.class, args);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 解析JSON
        ObjectMapper objectMapper = ContextAwareImpl.bean(ObjectMapper.class);
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));

        // 解析XML
        ObjectMapper xmlMapper = ContextAwareImpl.bean(ObjectMapper.class, "xmlMapper");
        converters.add(new MappingJackson2XmlHttpMessageConverter(xmlMapper));
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.modules(getJavaTimeModule()).createXmlMapper(false).build();
    }

    @Bean
    public ObjectMapper xmlMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.modules(getJavaTimeModule()).createXmlMapper(true).build();
    }

    private JavaTimeModule getJavaTimeModule() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        JavaTimeModule localDateTimeModule = new JavaTimeModule();
        localDateTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        localDateTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        return localDateTimeModule;
    }
}
