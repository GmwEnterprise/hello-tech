package org.example.common;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestCommon {

    @Test
    public void testDate() {
        System.out.println(new Date(System.currentTimeMillis() - 100 * 24 * 60 * 60 * 1000L));
    }
}
