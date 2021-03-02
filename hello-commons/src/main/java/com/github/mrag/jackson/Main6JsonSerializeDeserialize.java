package com.github.mrag.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main6JsonSerializeDeserialize {

    // https://www.baeldung.com/jackson
    // https://www.baeldung.com/jackson-annotations
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        People people = new People();
        people.setName("Mrag");
        people.setBirthday(LocalDate.now());
        System.out.println(mapper.writeValueAsString(people));
        // output: {"name":"Mrag","birthday":"2021/03/02"}
        // 如果加上了People上的注解，则输出：{"fieldName":"Mrag"}
    }

    // @JsonSerialize(using = PeopleSerialization.class)
    public static class People {
        private String name;

        @JsonSerialize(using = LocalDateSerialization.class)
        private LocalDate birthday;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }

    }

    private static class PeopleSerialization extends JsonSerializer<People> {
        @Override
        public void serialize(People value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("fieldName",value.getName());
            gen.writeEndObject();
        }
    }

    public static class LocalDateSerialization extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        }
    }
}
