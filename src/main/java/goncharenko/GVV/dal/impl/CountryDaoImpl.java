package goncharenko.GVV.dal.impl;

import goncharenko.GVV.dal.Dao;
import goncharenko.GVV.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Vitaliy on 31.10.2015.
 */

public class CountryDaoImpl implements Dao<Country> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public Country findById(String id) {
        try {
            LOGGER.info("Find Country by id - " + id);
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT * FROM Country c WHERE c.id ='" + id + "'", Country.class);
            Country country = (Country) query.getSingleResult();
            entityManager.getTransaction().commit();

            return country;
        } catch (Exception e) {
            LOGGER.error("Find Country by id - " + id + " is fail ", e, e.getMessage());
            entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public void delete(Country country) {
        try {
            LOGGER.info("Delete Country");
            entityManager.getTransaction().begin();
            entityManager.remove(country);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.error("Delete Country is fail ", e, e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void save(Country country) {
        try {
            LOGGER.info("Save Country");
            entityManager.getTransaction().begin();
            entityManager.persist(country);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.error("Save Country is fail ", e, e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }


    @Override
    public void saveList(List<Country> countryList) {
        try {
            LOGGER.info("Save Country list");
            entityManager.getTransaction().begin();
            for (Country country : countryList) {
                entityManager.persist(country);
            }
            entityManager.getTransaction().commit();
            entityManager.clear();
        } catch (Exception e) {
            LOGGER.error("Save Country list is fail ", e, e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }
}
