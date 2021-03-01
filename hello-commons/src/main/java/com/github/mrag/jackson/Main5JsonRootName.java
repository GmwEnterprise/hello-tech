package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Main5JsonRootName {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 默认不启用
        // 启用后，默认使用类名称作为ROOT_VALUE
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

        System.out.println(mapper.writeValueAsString(Gender.MALE));

        People people = new People();
        people.name = "Mrag";
        System.out.println(mapper.writeValueAsString(people));
    }

    // 设置 ROOT_VALUE 为 gender
    @JsonRootName("gender")
    public enum Gender {
        MALE, FEMALE
    }

    // 不设置
    public static class People {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
