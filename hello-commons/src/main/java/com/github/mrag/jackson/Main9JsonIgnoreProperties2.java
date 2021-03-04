package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main9JsonIgnoreProperties2 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Domain domain = new Domain();
        domain.setId(1000);
        domain.setName("Thinkpad");

        System.out.println(mapper.writeValueAsString(domain));
        // output: {"id":1000,"name":"Thinkpad"}

        String content = "{\"id\":1000,\"name\":\"Thinkpad\"}";
        Domain result = mapper.readValue(content, Domain.class);
        System.out.println(result.name);
    }

    public static class Domain {

        private Integer id;

        @JsonIgnore
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
