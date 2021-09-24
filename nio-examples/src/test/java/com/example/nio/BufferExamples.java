package com.example.nio;

import com.example.nio.common.CommonUtils;
import org.junit.jupiter.api.Test;

import java.nio.IntBuffer;

public class BufferExamples {

    @Test
    public void testIntBuffer() {
        IntBuffer buf = IntBuffer.allocate(10);

        buf.put(new int[]{1, 2, 3, 4});
        CommonUtils.print(buf);
        // capacity = 10, limit = 10, position = 4

        // 翻转为读模式
        buf.flip();
        CommonUtils.print(buf);
        // capacity = 10, limit = 4, position = 0


        // 读一半
        int[] res = new int[buf.remaining() / 2];
        int i = 0;
        while (i < res.length) {
            res[i++] = buf.get();
        }
        CommonUtils.print(res);

        // 压缩为写模式
        buf.compact();
        CommonUtils.print(buf);
        // capacity = 10, limit = 10, position = 2

        buf.put(new int[]{5, 6, 7});

        // 再次转换为读
        buf.flip();
        CommonUtils.print(buf);
        // capacity = 10, limit = 5, position = 0

        while (buf.hasRemaining()) {
            System.out.print(buf.get());
        }
        System.out.println();
        CommonUtils.print(buf);
        // capacity = 10, limit = 5, position = 5
    }
}
