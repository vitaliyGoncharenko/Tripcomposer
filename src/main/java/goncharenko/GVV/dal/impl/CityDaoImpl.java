package goncharenko.GVV.dal.impl;

import goncharenko.GVV.dal.Dao;
import goncharenko.GVV.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Vitaliy on 31.10.2015.
 */
public class CityDaoImpl implements Dao<City> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public City findById(String id) {
        try {
            LOGGER.info("Find City by id - " + id);
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT * FROM City c WHERE c.id ='" + id + "'", City.class);
            City city = (City) query.getSingleResult();
            entityManager.getTransaction().commit();

            return city;
        } catch (Exception e) {
            LOGGER.error("Find City by id - " + id + " is fail ", e, e.getMessage());
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void delete(City city) {
        try {
            LOGGER.info("Delete City");
            entityManager.getTransaction().begin();
            entityManager.remove(city);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.error("Delete City is fail ", e, e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void save(City city) {
        try {
            LOGGER.info("Save City");
            entityManager.getTransaction().begin();
            entityManager.persist(city);
            entityManager.getTransaction().commit();
            entityManager.clear();

        } catch (Exception e) {
            LOGGER.error("Save City is fail ", e, e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void saveList(List<City> cityList) {
        try {
            LOGGER.info("Save City list");
            entityManager.getTransaction().begin();
            for (City city : cityList) {
                entityManager.persist(city);
            }
            entityManager.getTransaction().commit();
            entityManager.clear();
        } catch (Exception e) {
            LOGGER.error("Save City list is fail ", e, e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }
}
