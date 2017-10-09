package com.provar.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class ChooseBrowser {

	public static WebDriver driver;
	@Test
	public static WebDriver getBrowser() {

		try {
			if(ReadPropertyFile.getProperty().get("browserType").equals("CH")){
				System.setProperty("webdriver.chrome.driver","/POM/drivers/win/chromedriver.exe");
				driver=new ChromeDriver();


			}else{
				if(ReadPropertyFile.getProperty().get("browserType").equals("FF")){
					System.setProperty("webdriver.gecko.driver","/POM/drivers/win/geckodriver.exe");
					driver=new FirefoxDriver();
				}else{
					if(ReadPropertyFile.getProperty().get("browserType").equals("IE")){
						System.setProperty("webdriver.ie.driver","/POM/drivers/win/IEDriverServer.exe");
						driver=new InternetExplorerDriver();
					}
				}

			}
		} 
		
		catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			driver.get(ReadPropertyFile.getProperty().get("baseUrl"));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;

	}
	


}
