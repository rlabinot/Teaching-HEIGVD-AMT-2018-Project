package ch.heig.amt.gamification.api.spec.helpers;


import ch.heig.amt.gamification.api.DefaultApi;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private DefaultApi api = new DefaultApi();
    private final String APPLICATION_NAME = "123";

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heig.amt.gamification.server.url");
        api.getApiClient().setBasePath(url);

    }

    public DefaultApi getApi() {
        return api;
    }


    public String getAPPLICATION_NAME() {
        return APPLICATION_NAME;
    }
}
