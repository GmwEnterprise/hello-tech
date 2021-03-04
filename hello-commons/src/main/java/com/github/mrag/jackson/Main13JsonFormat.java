package com.github.mrag.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Date;

public class Main13JsonFormat {
    public static void main(String[] args) throws JsonProcessingException {
        Domain domain = new Domain();
        domain.setDate(new Date());
        domain.setLocalDate(LocalDate.now());

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(domain));
        // 不加JsonFormat注解时输出：{"date":1614764571945,"localDate":{"year":2021,"month":"MARCH","dayOfMonth":3,"monthValue":3,"dayOfWeek":"WEDNESDAY","era":"CE","dayOfYear":62,"leapYear":false,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
        // 加了注解输出：
    }

    public static class Domain {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date date;
        private LocalDate localDate;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }
    }
}
