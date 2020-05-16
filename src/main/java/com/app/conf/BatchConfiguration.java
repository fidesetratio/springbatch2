package com.app.conf;

import javax.sql.DataSource;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class BatchConfiguration {

	
	
	 	@Bean
	    public JobRepository jobRepository(@Qualifier("batchdataSource") DataSource dataSource, @Qualifier("batchtransactionManager") DataSourceTransactionManager transactionManager) throws Exception {
	        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
	        jobRepositoryFactoryBean.setDataSource(dataSource);
	        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
	        return jobRepositoryFactoryBean.getObject();
	    }
	 	
	 	 @Bean
	     public JobLauncher jobLauncher(JobRepository jobRepository) {
	         SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
	         jobLauncher.setJobRepository(jobRepository);
	         return jobLauncher;
	     }

}
