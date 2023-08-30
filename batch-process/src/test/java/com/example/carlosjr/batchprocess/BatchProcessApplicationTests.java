package com.example.carlosjr.batchprocess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class BatchProcessApplicationTests {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @Test
    void testJob(CapturedOutput output) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        // given
        JobParameters jobParameters = new JobParameters();

        // when
        JobExecution jobExecution = this.jobLauncher.run(this.job, jobParameters);

        // then
        Assertions.assertTrue(output.getOut().contains("Processing the billing information!"));
        Assertions.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    }

}
