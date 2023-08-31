package com.example.carlosjr.batchprocess.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Component
public class Test implements CommandLineRunner {

    private final DataSource dataSource;
    private final JdbcTransactionManager transactionManager;


    public Test(DataSource dataSource) {
        this.dataSource = dataSource;
        transactionManager = new JdbcTransactionManager(dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("USE SGP");
        System.out.println("Connection is" + dataSource.getConnection());

    }
}
