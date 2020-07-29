package io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import io.nuite.datasources.DynamicDataSourceConfig;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
public class IntegralApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(IntegralApplication.class, args);
	}

}
