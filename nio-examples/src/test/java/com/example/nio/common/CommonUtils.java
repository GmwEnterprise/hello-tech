package com.example.nio.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CommonUtils {

    public static void print(Buffer buf) {
        System.out.printf("capacity = %d, limit = %d, position = %d%n",
                buf.capacity(), buf.limit(), buf.position());
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void printIOStream(InputStream input) throws IOException {
        byte[] buf = new byte[512];
        int read;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((read = input.read(buf, 0, buf.length)) != -1) {
            output.write(buf, 0, read);
        }
        System.out.println(output);
    }
}
