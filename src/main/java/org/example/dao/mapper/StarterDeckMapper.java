// src/main/java/org/example/dao/mapper/StarterDeckMapper.java
package org.example.dao.mapper;

import org.example.model.StarterDeck;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StarterDeckMapper implements RowMapper<StarterDeck> {
    @Override
    public StarterDeck map(ResultSet rs, StatementContext ctx) throws SQLException {
        StarterDeck s = new StarterDeck();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setReleaseDate(rs.getDate("release_date"));
        s.setDiscontinued(rs.getBoolean("discontinued"));
        s.setPrice(rs.getFloat("price"));
        s.setImage(rs.getString("image"));
        return s;
    }
}
