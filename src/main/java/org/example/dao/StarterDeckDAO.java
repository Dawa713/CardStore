package org.example.dao;

import org.example.model.StarterDeck;
import org.example.dao.mapper.StarterDeckMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(StarterDeckMapper.class)
public interface StarterDeckDAO {

    @SqlQuery("SELECT id, name, release_date, discontinued, price, image FROM starter_decks")
    List<StarterDeck> listAll();

    @SqlQuery("SELECT id, name, release_date, discontinued, price, image FROM starter_decks WHERE id = :id")
    Optional<StarterDeck> findById(@Bind("id") int id);

    @SqlUpdate("""
        INSERT INTO starter_decks(name, release_date, discontinued, price, image)
        VALUES (:name, :releaseDate, :discontinued, :price, :image)
    """)
    @org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
    int insert(@BindBean StarterDeck deck);

    @SqlUpdate("""
        UPDATE starter_decks
           SET name         = :name,
               release_date = :releaseDate,
               discontinued = :discontinued,
               price        = :price,
               image        = :image
         WHERE id = :id
    """)
    void update(@BindBean StarterDeck deck);

    @SqlUpdate("DELETE FROM starter_decks WHERE id = :id")
    void delete(@Bind("id") int id);

    /**
     * Búsqueda multi-criterio: por nombre (LIKE) y precio máximo.
     */
    @SqlQuery("""
      SELECT id,
             name,
             release_date,
             discontinued,
             price,
             image
      FROM starter_decks
      WHERE name     LIKE :term
        AND price   <= :maxPrice
    """)
    List<StarterDeck> searchDecks(@Bind("term") String term,
                                  @Bind("maxPrice") float maxPrice);
}
