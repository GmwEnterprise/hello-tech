package com.example.studentsystem.common;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public final class Enums {

    // @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public interface EnumInterface {

        // FIXME 当前系统只能按照name来进行枚举的序列化以及反序列化，缺少一种更好的方式
        @JsonValue
        String getName();

        int getValue();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T find(Class<T> type, int value) {
        Asserts.implementsEnumInterface(type);
        return (T) Arrays.stream(type.getEnumConstants())
                         .map(source -> ((EnumInterface) source))
                         .filter(item -> item.getValue() == value)
                         .findFirst().orElseThrow(() -> new RuntimeException("未找到对应枚举"));
    }

    // 状态，[1]入学[2]毕业
    public enum StudentStatus implements EnumInterface {
        Learning(1, "学业进行中"),
        Graduated(2, "已毕业");

        StudentStatus(int value, String name) {
            this.name = name;
            this.value = value;
        }

        private final int value;
        private final String name;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    // 选课情况，[1]课程进行中[2]课程结束
    public enum CourseSelectionStatus implements EnumInterface {
        CourseLearning(1, "开课中"),
        Finished(2, "课程完结");

        CourseSelectionStatus(int value, String name) {
            this.name = name;
            this.value = value;
        }

        private final int value;
        private final String name;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    @MappedTypes({StudentStatus.class, CourseSelectionStatus.class})
    @MappedJdbcTypes(JdbcType.TINYINT)
    public static class EnumsTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

        private final Class<E> type;

        public EnumsTypeHandler(Class<E> type) {
            super();
            Asserts.implementsEnumInterface(type);
            this.type = type;
        }

        @Override
        public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
            ps.setInt(i, ((Enums.EnumInterface) parameter).getValue());
        }

        @Override
        public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
            return Enums.find(type, rs.getInt(columnName));
        }

        @Override
        public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
            return Enums.find(type, rs.getInt(columnIndex));
        }

        @Override
        public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
            return Enums.find(type, cs.getInt(columnIndex));
        }
    }

}
