package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Main8JsonAlias {
    public static void main(String[] args) {

    }

    public static class Domain {
        @JsonAlias({"id", "no"})
        private Integer id;
        private String name;

        public Domain(Integer id, String name) {
            this.id = id;
            this.name = name;
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
