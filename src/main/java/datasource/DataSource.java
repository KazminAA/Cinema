package datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import helpers.PropertyHolder;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Alexandr on 12.09.2016.
 */
public class DataSource {
    private static DataSource dataSource;
    private static ComboPooledDataSource poolConections;

    private DataSource() {
        initPool();
    }

    public static synchronized DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }

    private static void initPool() {
        poolConections = new ComboPooledDataSource();
        PropertyHolder propertyHolder = PropertyHolder.getInstance();
        try {
            poolConections.setDriverClass(propertyHolder.getJdbcDriver());
            poolConections.setJdbcUrl(propertyHolder.getJdbcUrl());
            poolConections.setUser(propertyHolder.getJdbcUser());
            poolConections.setPassword(propertyHolder.getJdbcPassword());
            poolConections.setMinPoolSize(5);
            poolConections.setAcquireIncrement(1);
            poolConections.setMaxPoolSize(100);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = poolConections.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
