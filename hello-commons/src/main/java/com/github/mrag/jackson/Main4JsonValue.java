package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main4JsonValue {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        People people = new People();
        people.setId(1);
        people.setName("Mrag");
        people.setGender(Gender.MALE);

        System.out.println(mapper.writeValueAsString(people));
    }

    public static class People {
        private int id;
        private String name;
        private Gender gender;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "People{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    public enum Gender {
        MALE(100, "男"), FEMALE(200, "女");

        private final int value;
        private final String name;

        Gender(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        @JsonValue
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Gender{" +
                    "value=" + value +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
