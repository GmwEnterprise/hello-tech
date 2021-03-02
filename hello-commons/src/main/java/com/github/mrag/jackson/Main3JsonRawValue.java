package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main3JsonRawValue {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"attr\":false}";
        People people = new People();
        people.setId(1);
        people.setJson(json);

        System.out.println(mapper.writeValueAsString(people));
    }

    public static class People {
        private Integer id;

        private String json;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @JsonRawValue
        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }
    }
}
