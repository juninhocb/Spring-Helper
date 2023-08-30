package com.example.carlosjr.batchprocess.jobs;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.repository.JobRepository;

@RequiredArgsConstructor
public class BillingJob1 implements Job {

    private final JobRepository jobRepository;

    @Override
    public String getName() {
        return "Billing Job";
    }

    @Override
    public void execute(JobExecution execution) {
        System.out.println("Processing the billing information!");
        execution.setStatus(BatchStatus.COMPLETED);
        execution.setExitStatus(ExitStatus.COMPLETED);
        this.jobRepository.update(execution);
    }

    /*@Override
    public void execute(JobExecution execution) {
        try {
            throw new Exception("Unable to process billing information");
        } catch (Exception exception) {
            execution.addFailureException(exception);
            execution.setStatus(BatchStatus.COMPLETED);
            execution.setExitStatus(ExitStatus.FAILED.addExitDescription(exception.getMessage()));
        } finally {
            this.jobRepository.update(execution);
        }
    }*/
}
