package com.api.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DotEnvComponent {

	@Value("${DB_USERNAME}")
	private String dbUserName;

	@Value("${DB_PASSWORD}")
	private String dbPassword;

	public void printDbConfig() {
		//System.out.println("DB User Name: " + dbUserName);
		//System.out.println("DB Password: " + dbPassword);
	}
}
