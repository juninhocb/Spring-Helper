package com.example.carlosjr.batchprocess.config;

import com.example.carlosjr.batchprocess.domain.BillingData;
import com.example.carlosjr.batchprocess.tasks.FilePreparationTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.support.JdbcTransactionManager;

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
        return new JdbcTransactionManager(this.dataSource);
    }

    @Bean
    public FlatFileItemReader<BillingData> billingDataFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<BillingData>()
                .name("billingDataFileReader")
                .resource(new FileSystemResource("staging/billing-2023-01.csv"))
                .delimited()
                .names("dataYear", "dataMonth", "accountId", "phoneNumber", "dataUsage", "callDuration", "smsCount")
                .targetType(BillingData.class)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<BillingData> billingDataJdbcBatchItemWriter(){
        String sql = "insert into BILLING_DATA values (:dataYear, :dataMonth, :accountId, :phoneNumber, :dataUsage, :callDuration, :smsCount)";
        return new JdbcBatchItemWriterBuilder<BillingData>()
                .dataSource(this.dataSource)
                .sql(sql)
                .beanMapped()
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step1, Step step2){
        return new JobBuilder("BillingJob", jobRepository)
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, JdbcTransactionManager transactionManager){
        return new StepBuilder("filePreparation", jobRepository)
                .tasklet(new FilePreparationTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, JdbcTransactionManager jdbcTransactionManager,
                      ItemReader<BillingData> billingDataItemReader, ItemWriter<BillingData> billingDataItemWriter){
        return new StepBuilder("fileIngestion", jobRepository)
                .<BillingData, BillingData>chunk(100, jdbcTransactionManager)
                .reader(billingDataItemReader)
                .writer(billingDataItemWriter)
                .build();
    }

}
