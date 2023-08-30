package com.example.carlosjr.batchprocess.jobs;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;

@RequiredArgsConstructor
public class BillingJob2 implements Job {

    private final JobRepository jobRepository;

    @Override
    public String getName() {
        return "Billing Job";
    }

    @Override
    public void execute(JobExecution execution) {
        JobParameters jobParameters2 = new JobParametersBuilder()
                .addString("input.file", "/some/input/file")
                .addString("file.format", "csv", false)
                .toJobParameters();
        JobParameters jobParameters = jobParameters2;
        String inputFile = jobParameters.getString("input.file");
        System.out.println("Processing billing information from file " + inputFile);
        execution.setStatus(BatchStatus.COMPLETED);
        execution.setExitStatus(ExitStatus.COMPLETED);
        this.jobRepository.update(execution);
    }
}
