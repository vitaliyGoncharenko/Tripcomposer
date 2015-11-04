package goncharenko.GVV.logic.service.impl;

import goncharenko.GVV.logic.service.Connection;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vitaliy on 01.11.2015.
 */
@Service
public class ConnectionImpl implements Connection {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionImpl.class);
    private String name = "key";
    private String key = "$1$12309856$euBrWcjT767K2sP9MHcVS/";
    private String url = "http://tripcomposer.net/rest/test/countries/get";

    @Override
    public String getResponse() {
        StringBuffer response = null;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            LOGGER.info("Add request header");
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.append(name, key);

            LOGGER.info("Send post request");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(jsonObject.toString());
            wr.flush();
            wr.close();

            LOGGER.info("Get response");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            LOGGER.info("Response - " + response);
        } catch (Exception e) {
            LOGGER.error("Connect to http://tripcomposer.net/rest/test/countries/get is fail", e, e.getMessage());
        }
        return response.toString();
    }
}
