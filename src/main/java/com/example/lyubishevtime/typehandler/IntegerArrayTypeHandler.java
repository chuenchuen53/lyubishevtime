package com.example.lyubishevtime.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

@MappedTypes(List.class)
public class IntegerArrayTypeHandler extends BaseTypeHandler<List<Integer>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        Connection c = ps.getConnection();
        Integer[] array = parameter.toArray(new Integer[0]);
        Array sqlArray = c.createArrayOf("integer", array);
        ps.setArray(i, sqlArray);
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Array array = rs.getArray(columnName);
        Integer[] javaArray = (Integer[]) array.getArray();
        return Arrays.asList(javaArray);
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Array array = rs.getArray(columnIndex);
        Integer[] javaArray = (Integer[]) array.getArray();
        return Arrays.asList(javaArray);
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Array array = cs.getArray(columnIndex);
        Integer[] javaArray = (Integer[]) array.getArray();
        return Arrays.asList(javaArray);
    }
}