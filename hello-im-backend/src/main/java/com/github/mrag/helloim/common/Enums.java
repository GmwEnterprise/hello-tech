package com.github.mrag.helloim.common;

import java.util.Arrays;

public final class Enums {

    public interface EnumInterface {

        int getEnumValue();

        String getKeyName();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T find(Class<T> type, int value) {
        Asserts.implementsEnumInterface(type);
        return (T) Arrays.stream(type.getEnumConstants())
                         .map(source -> ((EnumInterface) source))
                         .filter(item -> item.getEnumValue() == value)
                         .findFirst().orElseThrow(() -> Exceptions.enumNotFound(type, value));
    }

    public enum UserStatus implements EnumInterface {

        NORMAL(1, "账户正常使用"),

        FORBIDDEN(2, "账户被封禁");

        private final int enumValue;
        private final String keyName;

        UserStatus(int enumValue, String keyName) {
            this.enumValue = enumValue;
            this.keyName = keyName;
        }

        @Override
        public int getEnumValue() {
            return enumValue;
        }

        @Override
        public String getKeyName() {
            return keyName;
        }
    }

    public enum MessageStatus implements EnumInterface {

        ALREADY_SEND(1, "消息已发送"),

        SEND_FAILED(2, "消息发送失败"),

        ALREADY_RECEIVED(3, "消息已接收");

        private final int enumValue;
        private final String keyName;

        MessageStatus(int enumValue, String keyName) {
            this.enumValue = enumValue;
            this.keyName = keyName;
        }

        @Override
        public int getEnumValue() {
            return enumValue;
        }

        @Override
        public String getKeyName() {
            return keyName;
        }
    }
}
