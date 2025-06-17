package org.example.dao.mapper;

import org.example.model.Card;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardMapper implements RowMapper<Card> {
    @Override
    public Card map(ResultSet rs, StatementContext ctx) throws SQLException {
        Card c = new Card();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setReleaseDate(rs.getDate("release_date").toLocalDate());
        c.setAttack(rs.getInt("attack"));
        c.setDefense(rs.getInt("defense"));
        c.setFoil(rs.getBoolean("is_foil"));
        c.setPrice(rs.getFloat("price"));
        c.setImage(rs.getString("image"));
        return c;
    }
}
