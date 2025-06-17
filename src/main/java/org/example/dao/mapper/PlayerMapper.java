package org.example.dao.mapper;

import org.example.model.Player;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper implements RowMapper<Player> {
    @Override
    public Player map(ResultSet rs, StatementContext ctx) throws SQLException {
        Player player = new Player();
        player.setId(rs.getInt("id"));
        player.setUsername(rs.getString("username"));
        player.setPassword(rs.getString("password"));
        player.setEmail(rs.getString("email"));
        player.setRole(rs.getString("role"));
        player.setLastLogin(rs.getTimestamp("last_login"));
        return player;
    }
}