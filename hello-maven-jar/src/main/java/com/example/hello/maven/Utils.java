package com.example.hello.maven;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public final class Utils {

    public static String inputStreamToString(InputStream input, Charset charset) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[64];
        int read;
        while ((read = input.read(buf)) != -1) {
            output.write(buf, 0, read);
        }
        input.close();
        byte[] array = output.toByteArray();
        return new String(array, charset);
    }
}
