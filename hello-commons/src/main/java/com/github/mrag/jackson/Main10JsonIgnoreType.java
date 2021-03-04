package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main10JsonIgnoreType {

    public static void main(String[] args) throws JsonProcessingException {
        User.Name name = new User.Name("John", "Doe");
        User user = new User(1, name);

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(user);
        System.out.println(result); // output: {"id":1}

        User user1 = mapper.readValue("{\"id\":1}", User.class);
        System.out.println(user1);
    }

    public static class User {
        public int id;
        public Name name;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name=" + name +
                    '}';
        }

        public User() {
        }

        public User(int id, Name name) {
            this.id = id;
            this.name = name;
        }

        @JsonIgnoreType
        public static class Name {
            public String firstName;
            public String lastName;

            public Name(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }
    }
}
