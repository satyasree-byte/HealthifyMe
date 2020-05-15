package com.App.HealthifyMe.Helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepoReader {

		
		static Properties properties;
		public static void ReadOR() throws IOException {// Load the properties File
			
			properties = new Properties();
			FileReader objfile = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\ObjectRepository.properties");
			properties.load(objfile);
				
		}
		
		public static String getObject(String Key) throws IOException {
			ReadOR();
			String locator = properties.getProperty(Key);
			return locator;
			
			
		}
	}
