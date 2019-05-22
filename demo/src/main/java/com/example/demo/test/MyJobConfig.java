package com.example.demo.test;


import com.example.demo.entity.creditBill;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableBatchProcessing
public class MyJobConfig {
    @Autowired
    public JobLauncher jobLauncher;
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    private MyCsvDataReader myCsvDataReader;
    @Autowired
    private MyCsvDataWriter myCsvDataWriter;


    //任务入口
    @Scheduled(initialDelay=10000, fixedRate = 60000)
    public void run() {
        try {
            JobParameters param =    new JobParametersBuilder().addString("date", "2019-05-22").toJobParameters();
            JobExecution execution = jobLauncher.run(myCsvDataJob(), param);//执行job
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //定义任务
    @Bean
    public Job myCsvDataJob() {
        return jobBuilderFactory.get("myCsvDataJob")
                .incrementer(new RunIdIncrementer())
                .listener(new MyCsvDataListener())
                .flow(myStep())
                .end()
                .build();
    }

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep")
                .<CreditBill, creditBill> chunk(10)
                .reader(myCsvDataReader.read())
                .processor(new MyCsvDataProcessor())
                .writer(myCsvDataWriter)
                .build();
    }



}
