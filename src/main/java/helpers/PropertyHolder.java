package helpers;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Alexandr on 12.09.2016.
 */
public class PropertyHolder {
    private static PropertyHolder propertyHolder;

    private boolean isInMemoryDb = true;
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private String jdbcDriver;
    private int clearingInterval;
    private int sessBreakeMin;

    private PropertyHolder() {
        loadProperties();
    }

    public static synchronized PropertyHolder getInstance() {
        if (propertyHolder == null) {
            propertyHolder = new PropertyHolder();
        }
        return propertyHolder;
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(PropertyHolder.class.getClassLoader().getResourceAsStream("application.properties"));
            isInMemoryDb = Boolean.valueOf(properties.getProperty("db.inMemory"));
            jdbcDriver = properties.getProperty("dbDriver");
            jdbcUser = properties.getProperty("dbUserLogin");
            jdbcPassword = properties.getProperty("dbUserPassword");
            jdbcUrl = properties.getProperty("jdbcUrl");
            clearingInterval = Integer.valueOf(properties.getProperty("clearingInterval"));
            sessBreakeMin = Integer.valueOf(properties.getProperty("sessBreakMin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isInMemoryDb() {
        return isInMemoryDb;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public int getClearingInterval() {
        return clearingInterval;
    }

    public int getSessBreakeMin() {
        return sessBreakeMin;
    }
}
