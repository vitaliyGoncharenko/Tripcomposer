package goncharenko.GVV.config;

/**
 * Created by Пользователь on 08.10.2015.
 */

import goncharenko.GVV.dal.Dao;
import goncharenko.GVV.dal.impl.CityDaoImpl;
import goncharenko.GVV.dal.impl.CountryDaoImpl;
import goncharenko.GVV.manager.Manager;
import goncharenko.GVV.manager.impl.ManagerImpl;
import goncharenko.GVV.logic.service.Connection;
import goncharenko.GVV.logic.service.impl.ConnectionImpl;
import goncharenko.GVV.model.City;
import goncharenko.GVV.model.Country;
import goncharenko.GVV.utils.JsonParser;
import goncharenko.GVV.utils.impl.JsonParserImpl;
import org.springframework.context.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan("goncharenko.GVV")
public class AppConfig {

    @Bean
    public Dao<Country> daoCountry() {
        return new CountryDaoImpl();
    }

    @Bean
    public Dao<City> daoCity() {
        return new CityDaoImpl();
    }

    @Bean
    public Manager managerImpl() {
        return new ManagerImpl();
    }

    @Bean
    public Connection connectionImpl() {
        return new ConnectionImpl();
    }

    @Bean
    public JsonParser jsonParserImpl() {
        return new JsonParserImpl();
    }

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tripcomposer");
        return emf.createEntityManager();
    }

}
