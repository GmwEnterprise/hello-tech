package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main11JsonInclude {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Domain domain = new Domain();
        domain.setId(null);
        domain.setName("Mrag");

        System.out.println(mapper.writeValueAsString(domain));
        // output: {"name":"Mrag"}
        // 去掉注解后: {"id":null,"name":"Mrag"}
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Domain {
        private Integer id;
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
