package com.example.carlosjr.batchprocess.config;

import com.example.carlosjr.batchprocess.jobs.BillingJob2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingJobConfig {

    @Bean
    public Job billingJob(JobRepository jobRepository) {
        return new BillingJob2(jobRepository);
    }

}
