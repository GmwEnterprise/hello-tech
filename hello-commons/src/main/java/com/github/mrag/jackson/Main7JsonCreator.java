package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main7JsonCreator {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String content = "{\"no\":100,\"title\":\"Fucker\"}";
        Domain domain = mapper.readValue(content, Domain.class);
        assert domain.id == 100;
        assert domain.name.equals("Fucker");
    }

    public static class Domain {
        private Integer id;
        private String name;

        @JsonCreator
        public Domain(@JsonProperty("no") Integer no,
                      @JsonProperty("title") String title) {
            this.id = no;
            this.name = title;
        }

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
