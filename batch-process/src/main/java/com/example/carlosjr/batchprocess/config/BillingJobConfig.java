package com.example.carlosjr.batchprocess.config;

import com.example.carlosjr.batchprocess.tasks.FilePreparationTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class BillingJobConfig {

    private final DataSource dataSource;

    public BillingJobConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
    @Bean
    public Job billingJob(JobRepository jobRepository) {
        return new BillingJob2(jobRepository);
    }*/

    /*@Bean
    public PlatformTransactionManager transactionManager() throws SQLException {

        return new DataSourceTransactionManager(this.dataSource);
    }*/

    @Bean
    public JdbcTransactionManager jdbcTransactionManager() throws SQLException {
        System.out.println("connection: " + this.dataSource.getConnection().getSchema());
        return new JdbcTransactionManager(this.dataSource);
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step1){
        return new JobBuilder("BillingJob", jobRepository)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, JdbcTransactionManager transactionManager){
        return new StepBuilder("filePreparation", jobRepository)
                .tasklet(new FilePreparationTasklet(), transactionManager)
                .build();
    }

}
