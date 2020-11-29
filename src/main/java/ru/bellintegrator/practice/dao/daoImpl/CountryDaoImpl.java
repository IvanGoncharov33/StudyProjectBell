package ru.bellintegrator.practice.dao.daoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.CatalogDao;
import ru.bellintegrator.practice.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * {@inheritDoc}
 */
@AllArgsConstructor
@Repository
public class CountryDaoImpl implements CatalogDao<Country> {

    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getList() {

        Query query = entityManager.createNativeQuery("SELECT * FROM country", Country.class);
        List<Country> countryList = (List<Country>)query.getResultList();
        return countryList;
    }
}
