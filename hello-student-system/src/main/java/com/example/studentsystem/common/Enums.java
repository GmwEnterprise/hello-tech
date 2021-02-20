package com.example.studentsystem.common;

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

    public interface EnumInterface {

        String key();

        int value();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T find(Class<T> type, int value) {
        Asserts.implementsEnumInterface(type);
        return (T) Arrays.stream(type.getEnumConstants())
                         .map(source -> ((EnumInterface) source))
                         .filter(item -> item.value() == value)
                         .findFirst().orElseThrow(() -> new RuntimeException("未找到对应枚举"));
    }

    // 状态，[1]入学[2]毕业
    public enum StudentStatus implements EnumInterface {
        Learning(1, "学业进行中"),
        Graduated(2, "已毕业");

        StudentStatus(int value, String key) {
            this.key = key;
            this.value = value;
        }

        private final int value;
        private final String key;

        @Override
        public String key() {
            return key;
        }

        @Override
        public int value() {
            return value;
        }
    }

    // 选课情况，[1]课程进行中[2]课程结束
    public enum CourseSelectionStatus implements EnumInterface {
        CourseLearning(1, "课程学习中"),
        Finished(2, "课程学习结束");

        CourseSelectionStatus(int value, String key) {
            this.key = key;
            this.value = value;
        }

        private final int value;
        private final String key;

        @Override
        public String key() {
            return key;
        }

        @Override
        public int value() {
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
            ps.setInt(i, ((Enums.EnumInterface) parameter).value());
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
