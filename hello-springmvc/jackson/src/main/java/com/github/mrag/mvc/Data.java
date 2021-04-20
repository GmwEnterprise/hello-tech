package com.github.mrag.mvc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;

// @JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Data {

    private DataType dataType;
    private String serializedValue;
    private LocalDateTime serializingTimestamp;

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getSerializedValue() {
        return serializedValue;
    }

    public void setSerializedValue(String serializedValue) {
        this.serializedValue = serializedValue;
    }

    public LocalDateTime getSerializingTimestamp() {
        return serializingTimestamp;
    }

    public void setSerializingTimestamp(LocalDateTime serializingTimestamp) {
        this.serializingTimestamp = serializingTimestamp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("dataType", dataType)
                .append("serializedValue", serializedValue)
                .append("serializingTimestamp", serializingTimestamp)
                .toString();
    }

    public enum DataType {
        STRING(1, "字符串");

        private final int value;
        private final String desc;

        DataType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        @JsonCreator
        public static DataType choose(int value) {
            return Arrays.stream(values())
                         .filter(v -> v.value == value)
                         .findAny().orElse(null);
        }

        @Override
        @JsonValue
        public String toString() {
            return String.format("Enum<%s>[%s, %s]", DataType.class.getSimpleName(), value, desc);
        }
    }
}
