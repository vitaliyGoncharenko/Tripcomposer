package goncharenko.GVV.manager.impl;

import goncharenko.GVV.dal.Dao;
import goncharenko.GVV.logic.service.Connection;
import goncharenko.GVV.manager.Manager;
import goncharenko.GVV.model.City;
import goncharenko.GVV.model.Country;
import goncharenko.GVV.utils.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Created by Vitaliy on 02.11.2015.
 */
public class ManagerImpl implements Manager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerImpl.class);

    @Autowired
    private Dao<City> cityDao;

    @Autowired
    private Dao<Country> countryDao;

    @Autowired
    private Connection connection;

    @Autowired
    private JsonParser jsonParser;

    @Override
    public void saveResponse() {
        LOGGER.info("Connect to http://tripcomposer.net/rest/test/countries/get and parse response");
        List<Country> countryList = jsonParser.parseResponseToCountryList(connection.getResponse());
        for (Country country : countryList) {
            Set<City> cityList = country.getCities();
            for (City city : cityList) {
                cityDao.save(city);
            }
            countryDao.save(country);
        }
        LOGGER.info("Save all countries and cities to database is complete");
    }
}
