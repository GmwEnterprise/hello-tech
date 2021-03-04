package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main9JsonIgnoreProperties {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // {"name":"Mrag"}, {"name":"Mrag","title":"Hello!"}
        String content = "{\"name\":\"Mrag\",\"title\":\"Hello!\"}";
        Domain domain = mapper.readValue(content, Domain.class);
        assert domain.id == null;
        assert domain.name.equals("Mrag");

        domain.id = 100;
        System.out.println(mapper.writeValueAsString(domain));
        // output: {"name":"Mrag"}
        // 如果忽略的字段并没有标注在Domain上的注解上，id在序列化时并不会被忽略
    }

    // ignoreUnknown在反序列化时可以忽略未匹配字段，但必须标注在类上才生效
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Domain {
        private Integer id;
        private String name;

        @JsonIgnoreProperties(value = {"id"})
        public static Domain create(@JsonProperty("id") Integer id,
                                    @JsonProperty("name") String name) {
            Domain domain = new Domain();
            domain.setId(id);
            domain.setName(name);
            return domain;
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
