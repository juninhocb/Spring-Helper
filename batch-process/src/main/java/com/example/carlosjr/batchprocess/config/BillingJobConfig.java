package com.example.carlosjr.batchprocess.config;

import com.example.carlosjr.batchprocess.domain.BillingData;
import com.example.carlosjr.batchprocess.domain.ReportingData;
import com.example.carlosjr.batchprocess.tasks.BillingDataProcessor;
import com.example.carlosjr.batchprocess.tasks.FilePreparationTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.DataClassRowMapper;
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

    //File
    @Bean
    @StepScope
    public FlatFileItemReader<BillingData> billingDataFlatFileItemReader(@Value("#{jobParameters['input.file']}") String inputFile) {
        return new FlatFileItemReaderBuilder<BillingData>()
                .name("billingDataFileReader")
                .resource(new FileSystemResource(inputFile))
                .delimited()
                .names("dataYear", "dataMonth", "accountId", "phoneNumber", "dataUsage", "callDuration", "smsCount")
                .targetType(BillingData.class)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<ReportingData> billingDataFileWriter(
            @Value("#{jobParameters['output.file']}") String outputFile) {
        return new FlatFileItemWriterBuilder<ReportingData>()
                .resource(new FileSystemResource("staging/billing-report-2023-01.csv"))
                .name("billingDataFileWriter")
                .delimited()
                .names("billingData.dataYear", "billingData.dataMonth", "billingData.accountId", "billingData.phoneNumber", "billingData.dataUsage", "billingData.callDuration", "billingData.smsCount", "billingTotal")
                .build();
    }


    //Database
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
    @StepScope
    public JdbcCursorItemReader<BillingData> billingDataJdbcCursorItemReader(
            @Value("#{jobParameters['data.year']}") Integer year,
            @Value("#{jobParameters['data.month']}") Integer month
    ){
        String sql = String.format("select * from BILLING_DATA where DATA_YEAR = %d and DATA_MONTH = %d", year, month);
        JdbcCursorItemReader<BillingData> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(this.dataSource);
        reader.setName("billingDataTableReader");
        reader.setSql(sql);
        reader.setRowMapper(new DataClassRowMapper<>(BillingData.class));
        return reader;
    }

    //Item process

    @Bean
    public BillingDataProcessor billingDataProcessor(){
        return new BillingDataProcessor();
    }

    //JOB
    @Bean
    public Job job(JobRepository jobRepository, Step step1, Step step2, Step step3){
        return new JobBuilder("BillingJob", jobRepository)
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    //STEPS
    @Bean
    public Step step1(JobRepository jobRepository, JdbcTransactionManager transactionManager){
        return new StepBuilder("filePreparation", jobRepository)
                .tasklet(new FilePreparationTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, JdbcTransactionManager jdbcTransactionManager,
                      @Qualifier("billingDataFlatFileItemReader") ItemReader<BillingData> billingDataItemReader, ItemWriter<BillingData> billingDataItemWriter){
        return new StepBuilder("fileIngestion", jobRepository)
                .<BillingData, BillingData>chunk(100, jdbcTransactionManager)
                .reader(billingDataItemReader)
                .writer(billingDataItemWriter)
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, JdbcTransactionManager transactionManager,
                      @Qualifier("billingDataJdbcCursorItemReader")  ItemReader<BillingData> billingDataTableReader,
                      ItemProcessor<BillingData, ReportingData> billingDataProcessor,
                      ItemWriter<ReportingData> billingDataFileWriter) {
        return new StepBuilder("reportGeneration", jobRepository)
                .<BillingData, ReportingData>chunk(100, transactionManager)
                .reader(billingDataTableReader)
                .processor(billingDataProcessor)
                .writer(billingDataFileWriter)
                .build();
    }



}
