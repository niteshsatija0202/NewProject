package com.provar.utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

public class ReadPropertyFile {
	
	@Test
	public static Map<String,String> getProperty() throws Throwable{

		Properties pro=new Properties();
		Map<String,String> propMap=new HashMap<String, String>();
		FileInputStream file=new FileInputStream("/POM/resources/config.properties");
		pro.load(file);

		for(String Key:pro.stringPropertyNames()){
			
			propMap.put(Key,pro.getProperty(Key));

		}
		return propMap;
	}
	
}
