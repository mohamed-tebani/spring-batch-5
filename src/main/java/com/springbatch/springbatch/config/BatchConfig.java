package com.springbatch.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfiguration {

    @Bean
    public Job createJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("MyJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(firstStep(jobRepository, transactionManager))
                .end()
                .build();
    }

    @Bean
    public Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("MyStep", jobRepository)
                .<String, String>chunk(1, transactionManager)
                .reader(new MyCustomReader())
                .processor(new MyCustomProcessor())
                .writer(new MyCustomWriter())
                .build();
    }
}
