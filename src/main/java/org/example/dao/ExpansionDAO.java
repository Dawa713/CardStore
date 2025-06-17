package org.example.dao;

import org.example.model.Expansion;
import org.example.dao.mapper.ExpansionMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(ExpansionMapper.class)
public interface ExpansionDAO {

    @SqlQuery("SELECT id, name, release_date, discontinued, price, image FROM expansions")
    List<Expansion> listAll();

    @SqlQuery("SELECT id, name, release_date, discontinued, price, image FROM expansions WHERE id = :id")
    Optional<Expansion> findById(@Bind("id") int id);

    @SqlUpdate("""
        INSERT INTO expansions(name, release_date, discontinued, price, image)
        VALUES (:name, :releaseDate, :discontinued, :price, :image)
    """)
    @org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
    int insert(@BindBean Expansion exp);

    @SqlUpdate("""
        UPDATE expansions
           SET name         = :name,
               release_date = :releaseDate,
               discontinued = :discontinued,
               price        = :price,
               image        = :image
         WHERE id = :id
    """)
    void update(@BindBean Expansion exp);

    @SqlUpdate("DELETE FROM expansions WHERE id = :id")
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
      FROM expansions
      WHERE name     LIKE :term
        AND price   <= :maxPrice
    """)
    List<Expansion> searchExpansions(@Bind("term") String term,
                                     @Bind("maxPrice") float maxPrice);
}
