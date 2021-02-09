package com.github.mrag.helloim.common;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({Enums.AnswerStatus.class, Enums.MessageStatus.class})
@MappedJdbcTypes(JdbcType.TINYINT)
public class EnumsTypeHandler<E extends Enum<E>> implements TypeHandler<E> {

    private final Class<E> type;

    public EnumsTypeHandler(Class<E> type) {
        Asserts.implementsEnumInterface(type);
        this.type = type;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, ((Enums.EnumInterface) parameter).getEnumValue());
    }

    @Override
    public E getResult(ResultSet rs, int columnIndex) throws SQLException {
        return Enums.find(type, rs.getInt(columnIndex));
    }

    @Override
    public E getResult(ResultSet rs, String columnName) throws SQLException {
        return Enums.find(type, rs.getInt(columnName));
    }

    @Override
    public E getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Enums.find(type, cs.getInt(columnIndex));
    }
}

