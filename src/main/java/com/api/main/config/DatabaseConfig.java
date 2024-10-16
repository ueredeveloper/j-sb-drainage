package com.api.main.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:.env")
public class DatabaseConfig {

	@Value("${DATABASE_USERNAME}")
	private String dbUserName;

	@Value("${DATABASE_PASSWORD}")
	private String dbPassword;
	
	@Value("${DATABASE_URL}")
	private String dbUrl;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();

		config.setUsername(dbUserName);
		config.setPassword(dbPassword);
		config.setJdbcUrl(dbUrl);
		return new HikariDataSource(config);
	}
}
