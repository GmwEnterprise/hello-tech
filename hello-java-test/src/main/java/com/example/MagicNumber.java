package com.example;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MagicNumber {

    public static void main(String[] args) throws IOException {
        InputStream input = new FileInputStream("D:\\Projects\\hello-tech\\hello-java-test\\target\\classes\\com\\example\\GCTest.class");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[128];
        int read;
        while ((read = input.read(buffer)) != -1) {
            output.write(buffer, 0, read);
        }
        System.out.println(Arrays.toString(output.toByteArray())); // -54, -2, -70, -66，翻译为16进制刚好就是 0xCAFEBABE
        input.close();
    }
}
