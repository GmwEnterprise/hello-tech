package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main1 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        People value = new People();
        value.id = 1;
        value.name = "Mrag";
        System.out.println(mapper.writeValueAsString(value));

        Object o = mapper.readerFor(People.class).readValue("{\"id\":1,\"FuckName\":\"Mrag\"}");
        System.out.println(o);
    }

    public static class People {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @JsonGetter("name")
        public String getFuckName() {
            return name;
        }

        @JsonSetter("FuckName")
        public void setName(String name) {
            this.name = name;
        }
    }
}
