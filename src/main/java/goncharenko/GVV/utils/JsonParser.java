package goncharenko.GVV.utils;

import goncharenko.GVV.model.Country;

import java.util.List;

/**
 * Created by Vitaliy on 02.11.2015.
 */
public interface JsonParser {
    List<Country> parseResponseToCountryList(String response);
}
