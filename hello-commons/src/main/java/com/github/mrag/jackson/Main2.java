package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("city", "重庆");
        properties.put("name", "Mrag");

        People people = new People();
        people.setId(1);
        people.setProperties(properties);

        System.out.println(mapper.writeValueAsString(people));
        // output: {"id":1,"city":"重庆","name":"Mrag"}

        Object o = mapper.readerFor(People.class)
                         .readValue("{\"id\":1,\"city\":\"重庆\",\"name\":\"Mrag\"}");
        System.out.println(((People) o).getProperties());
        // output: {city=重庆, name=Mrag}
    }

    public static class People {
        private Integer id;

        // jackson不会为map类型初始化实例
        @JsonAnySetter
        private Map<String, Object> properties = new HashMap<>();

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @JsonAnyGetter
        public Map<String, Object> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, Object> properties) {
            this.properties = properties;
        }
    }
}
