package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main6JsonDeserialize {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        // 反序列化时需要配置
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);

        String text = "{\"DomainRoot\": {\"date\": \"2021/01/31\"}}";
        Domain domain = mapper.readValue(text, Domain.class);
        System.out.println(domain.getDate());
        // output: 2021-01-31
    }

    @JsonRootName("DomainRoot")
    public static class Domain {
        @JsonDeserialize(using = DateDeserializer.class)
        private LocalDate date;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }
    }

    public static class DateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
            String text = p.getText();
            System.out.println(text);
            // output: 2021/01/31
            return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }
    }
}
