package com.api.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EnvLoader {

	public static void loadEnvFile(String filePath) {
		File envFile = new File(filePath);
		if (!envFile.exists()) {
			System.out.println(".env file not found: " + filePath);
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(envFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Ignore comments and empty lines
				if (line.trim().isEmpty() || line.startsWith("#")) {
					continue;
				}

				// Split the line by '=' to get key and value
				String[] parts = line.split("=", 2);
				if (parts.length == 2) {
					String key = parts[0].trim();
					String value = parts[1].trim();
					// Set the system property
					
					System.out.println(key + ", " + value);
					System.setProperty(key, value);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
