package com.Hibernate.ProjectHome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProjectHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectHomeApplication.class, args);
	}

}
