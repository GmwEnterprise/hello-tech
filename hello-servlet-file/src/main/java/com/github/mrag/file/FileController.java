package com.github.mrag.file;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Gmw
 */
@RestController
public class FileController {

    private static final String SYSTEM_TEMP_DIR = System.getProperty("java.io.tmpdir");

    @PostMapping(value = "/upload")
    public void upload(@RequestParam("dataf") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());

        InputStream fileInput = file.getInputStream();
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        byte[] buf = new byte[10000];
        int n, length = 0;
        while ((n = fileInput.read(buf)) != -1) {
            length += n;
            byteOut.write(buf, 0, buf.length);
        }

        byte[] bytes = byteOut.toByteArray();
        System.out.println(bytes.length);

        byte[] res = new byte[length];
        System.arraycopy(bytes, 0, res, 0, length);

        String xml = new String(res, StandardCharsets.UTF_8);
        System.out.println(xml);
    }
}
