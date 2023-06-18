package com.tsarenko.demo.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AvatarRowMapper implements RowMapper<byte[]> {
    @Override
    public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getBytes(1);
    }
}
