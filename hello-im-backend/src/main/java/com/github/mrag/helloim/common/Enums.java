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

        Normal(1, "账户正常使用"),

        Forbidden(2, "账户被封禁");

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

        AlreadySend(1, "消息已发送"),

        SendFailed(2, "消息发送失败"),

        AlreadyReceived(3, "消息已接收");

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

    // 消息应答情况：[0]非应答消息, [1]未应答， [2]已应答
    public enum AnswerStatus implements EnumInterface {

        NonResponseMessage(0, "非应答消息"),

        NoResponse(1, "未应答"),

        Answered(2, "已应答");

        private final int enumValue;
        private final String keyName;

        AnswerStatus(int enumValue, String keyName) {
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

    // 用户B对于用户A的身份：[1]好友, [2]黑名单
    public enum IdentityState implements EnumInterface {

        Friend(1, "好友"),

        BlackList(2, "黑名单");

        private final int enumValue;
        private final String keyName;

        IdentityState(int enumValue, String keyName) {
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

    // 用户A对用户B的消息权限：[1]接收消息, [2]屏蔽消息, [3]特别关心
    public enum ActionState implements EnumInterface {

        ReceiveMessage(1, "接收消息"),

        BlockMessage(2, "屏蔽消息"),

        SpecialConcern(3, "特别关心");

        private final int enumValue;
        private final String keyName;

        ActionState(int enumValue, String keyName) {
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
