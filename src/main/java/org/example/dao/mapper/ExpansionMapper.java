package org.example.dao.mapper;

import org.example.model.Expansion;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpansionMapper implements RowMapper<Expansion> {
    @Override
    public Expansion map(ResultSet rs, StatementContext ctx) throws SQLException {
        Expansion e = new Expansion();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setReleaseDate(rs.getDate("release_date"));
        e.setDiscontinued(rs.getBoolean("discontinued"));
        e.setPrice(rs.getFloat("price"));
        e.setImage(rs.getString("image"));
        return e;
    }
}
