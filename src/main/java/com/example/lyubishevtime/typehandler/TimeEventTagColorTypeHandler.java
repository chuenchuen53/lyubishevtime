package com.example.lyubishevtime.typehandler;

import com.example.lyubishevtime.entity.TimeEventTagColor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(TimeEventTagColor.class)
@Component
public class TimeEventTagColorTypeHandler extends BaseTypeHandler<TimeEventTagColor> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TimeEventTagColor parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name().toLowerCase());
    }

    @Override
    public TimeEventTagColor getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String name = rs.getString(columnName);
        return name == null ? null : TimeEventTagColor.valueOf(name.toUpperCase());
    }

    @Override
    public TimeEventTagColor getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String name = rs.getString(columnIndex);
        return name == null ? null : TimeEventTagColor.valueOf(name.toUpperCase());
    }

    @Override
    public TimeEventTagColor getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String name = cs.getString(columnIndex);
        return name == null ? null : TimeEventTagColor.valueOf(name.toUpperCase());
    }
}