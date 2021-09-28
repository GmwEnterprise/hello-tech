package com.example;

import java.lang.reflect.Field;

public class TestString {

    public static void main(String[] args) {
        String s = "Mrag"; // "Mrag"的引用会被保存到字符串常量池
        String s2 = new String(s);
        if (args.length > 0) {
            String input = args[0]; // 运行时接收的字符串，假定输入值为"Mrag"
            System.out.println(s == input);             // false
            System.out.println(s == input.intern());    // true
            System.out.println(s2 == input.intern());    // false
            System.out.println(ref(s2, "value") == ref(input.intern(), "value"));    // true
        } else {
            System.out.println("请输入启动参数");
        }
    }

    public static Object ref(Object o, String field) {
        try {
            Field f = o.getClass().getDeclaredField(field);
            f.setAccessible(true);
            return f.get(o);
        } catch (Exception e) {
            return null;
        }
    }
}
