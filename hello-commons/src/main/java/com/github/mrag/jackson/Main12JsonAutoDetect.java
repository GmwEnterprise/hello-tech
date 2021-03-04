package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main12JsonAutoDetect {
    public static void main(String[] args) throws JsonProcessingException {
        Domain domain = new Domain(2, "Mrag");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(domain));

        String content = "{\"id\":2,\"title\":\"Mrag\"}";
        Domain domain1 = mapper.readValue(content, Domain.class);
        System.out.println(domain1.id);
        System.out.println(domain1.title);
        // output: {"id":2,"title":"Mrag"}
        //         2
        //         Mrag
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
                    creatorVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Domain {
        private Integer id;
        private String title;

        private Domain() {}

        public Domain(Integer id, String title) {
            this.id = id;
            this.title = title;
        }
    }
}
