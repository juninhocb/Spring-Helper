package com.example.springiotenantapp.tenant;

import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component
public class ConnectionProvider implements MultiTenantConnectionProvider, HibernatePropertiesCustomizer {

    @Override
    public Connection getAnyConnection() throws SQLException {
        return getConnection("public");
    }
    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }
    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        String jdbcUrlWithSchema = "jdbc:mysql://localhost:3306/" + tenantIdentifier;
        DataSource dataSourceWithSchema = DataSourceBuilder.create()
                .url(jdbcUrlWithSchema)
                .username("instrument")
                .password("password")
                .build();
        return dataSourceWithSchema.getConnection();
    }
    @Override
    public void releaseConnection(String s, Connection connection) throws SQLException {
        System.out.println("closing connection.");
        connection.close();
    }
    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }
    @Override
    public boolean isUnwrappableAs(Class<?> aClass) {
        return false;
    }
    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, this);
    }
}
