package org.example.dao;

import org.example.model.Card;
import org.example.dao.mapper.CardMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(CardMapper.class)
public interface CardDAO {
    @SqlQuery("""
      SELECT id,
             name,
             release_date,
             attack,
             defense,
             is_foil,
             price,
             image
        FROM cards
    """)
    List<Card> listAll();

    @SqlQuery("""
      SELECT id,
             name,
             release_date,
             attack,
             defense,
             is_foil,
             price,
             image
        FROM cards
       WHERE id = :id
    """)
    Optional<Card> findById(@Bind("id") int id);

    @SqlUpdate("""
        INSERT INTO cards(name, release_date, attack, defense, is_foil, price, image)
        VALUES (:name, :releaseDate, :attack, :defense, :foil, :price, :image)
    """)
    @org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
    int insert(@BindBean Card card);

    @SqlUpdate("""
        UPDATE cards
           SET name         = :name,
               release_date = :releaseDate,
               attack       = :attack,
               defense      = :defense,
               is_foil      = :foil,
               price        = :price,
               image        = :image
         WHERE id = :id
    """)
    void update(@BindBean Card card);

    @SqlUpdate("DELETE FROM cards WHERE id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("""
      SELECT id,
             name,
             release_date,
             attack,
             defense,
             is_foil,
             price,
             image
        FROM cards
       WHERE name  LIKE :term
         AND price <= :maxPrice
    """)
    List<Card> searchCards(@Bind("term") String term,
                           @Bind("maxPrice") float maxPrice);
}
