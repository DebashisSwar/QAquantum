package QuantumInvertions.Selenium_Cucumber;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class accessConfigData {
	String dir = System.getProperty("user.dir");
	private Properties properties;

	private final String configFilePath= dir+"//Config_Details_Host.properties";


	public accessConfigData() {


	File  ConfigFile=new File(configFilePath);


	try {

	FileInputStream configFileReader=new FileInputStream(ConfigFile);

	      properties = new Properties();

	      properties.load(configFileReader);

	      configFileReader.close();

	    } catch (IOException e) 

	      {

	        System.out.println(e.getMessage());

	      }

	  }
	public String getApplicationUrl() {
		String applicationurl= properties.getProperty("url.base");
		return applicationurl;
	}
	public String getBrowserUrl() {
		String browser= properties.getProperty("browser");
		return browser;
	}
	public String getEnv() {
		String browser= properties.getProperty("environment");
		return browser;
	}
	public String getMode() {
		String mode= properties.getProperty("headless");
		return mode;
	}
}
