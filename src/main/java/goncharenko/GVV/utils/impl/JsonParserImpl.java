package goncharenko.GVV.utils.impl;


import goncharenko.GVV.model.City;
import goncharenko.GVV.model.Country;
import goncharenko.GVV.utils.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vitaliy on 02.11.2015.
 */
public class JsonParserImpl implements JsonParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParserImpl.class);

    @Override
    public List<Country> parseResponseToCountryList(String response) {
        List<Country> countries = new ArrayList<>();

        LOGGER.info("Parse string response to JSONObject");
        JSONObject json = null;
        try {
            json = (JSONObject) new JSONParser().parse(response);
        } catch (ParseException e) {
            LOGGER.error("Parse string response to JSONObject is fail", e, e.getMessage());
        }

        LOGGER.info("Get countries json array");
        JSONArray countriesJSONArray = (JSONArray) json.get("countries");

        for (int i = 0; i < countriesJSONArray.size(); i++) {
            LOGGER.info("Get country =" + countriesJSONArray.get(i));
            String countriesString = countriesJSONArray.get(i).toString();

            //get cities Json
            JSONObject countriesJson = null;
            try {
                countriesJson = (JSONObject) new JSONParser().parse(countriesString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONArray citiesJSONArray = (JSONArray) countriesJson.get("cities");

            LOGGER.info("Get cities");
            Set<City> cities = new HashSet<>();
            for (int j = 0; j < citiesJSONArray.size(); j++) {
                LOGGER.info("Get cities in json =" + citiesJSONArray.get(j));
                JSONObject cityJson = (JSONObject) citiesJSONArray.get(j);
                City city = new City();
                String cityName = cityJson.get("cityName").toString();
                LOGGER.info("Get city with name" + cityName);
                city.setCityName(cityName);
                cities.add(city);
            }

            LOGGER.info("Get country name" + countriesJson.get("countryName"));

            //set fields Country
            Country country = new Country();
            country.setCountryName(countriesJson.get("countryName").toString());
            country.setCountryISOCode(countriesJson.get("countryISOCode").toString());
            country.setCities(cities);

            countries.add(country);
        }
        return countries;
    }
}
