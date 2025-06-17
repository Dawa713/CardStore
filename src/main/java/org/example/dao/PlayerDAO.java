package org.example.dao;

import org.example.model.Player;
import org.example.dao.mapper.PlayerMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(PlayerMapper.class)
public interface PlayerDAO {

    @SqlQuery("SELECT id, username, password, email, role, last_login FROM players")
    List<Player> listarTodos();

    @SqlQuery("SELECT id, username, password, email, role, last_login FROM players WHERE id = :id")
    Optional<Player> findById(@Bind("id") int id);

    @SqlUpdate("INSERT INTO players(username, password, email, role, last_login) " +
            "VALUES (:username, :password, :email, :role, :lastLogin)")
    void insertar(@BindBean Player player);


    @SqlUpdate("DELETE FROM players WHERE id = :id")
    void eliminar(@Bind("id") int id);

    @SqlUpdate("""
    UPDATE players
       SET username   = :username,
           password   = :password,
           email      = :email,
           role       = :role,
           last_login = :lastLogin
     WHERE id = :id
""")
    void actualizar(@BindBean Player player);

    @SqlQuery("SELECT id, username, password, email, role, last_login "
            + "FROM players WHERE username = :username AND password = :password")
    Optional<Player> authenticate(@Bind("username") String username,
                                  @Bind("password") String password);

}
