package com.example.nio;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelExamples {

    @Test
    public void testReadFileChannel() throws Exception {
        FileChannel fc = new FileInputStream("temp.bak").getChannel();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteBuffer buf = ByteBuffer.allocate(8);

        while (fc.read(buf) != -1) {
            buf.flip();
            output.write(buf.array(), 0, buf.limit());
            buf.clear();
        }

        System.out.println(output);
        fc.close();
    }

    @Test
    public void testWriteFileChannel() throws Exception {
        FileChannel fc = new RandomAccessFile("temp.bak", "rw").getChannel();
        fc.write(ByteBuffer.wrap("\nMrag is here.\n".getBytes()));
        fc.close();
    }
}
