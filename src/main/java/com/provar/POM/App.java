package com.provar.POM;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.provar.object.LoginPage;
import com.provar.utilities.ChooseBrowser;
import com.provar.utilities.ReadExcel;

/**
 * Hello world!
 *
 */
public class App 

{


	public static void main( String[] args ) throws Throwable

	{ 

		System.setProperty("webdriver.chrome.driver","/POM/drivers/win/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com/");

		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("nitesh@provar.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("nick0202");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		driver.findElement(By.xpath("//a[@title='Accounts Tab']")).click();
		String search="All Accounts";
		// List<WebElement> list1=new Select(driver.findElement(By.xpath("//select[@id='fcf']"))).getOptions();
		String selected=driver.findElement(By.xpath("//select[@id='fcf']/option[@selected]")).getText();


		if (selected.equals(search)){

			driver.findElement(By.xpath("//input[@value=' Go! ']")).click();


		}else{
			new Select(driver.findElement(By.xpath("//select[@id='fcf']"))).selectByVisibleText(search);

		}
		List<WebElement> account_names= driver.findElements(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-Name']/a"));
	    String[][] input=ReadExcel.read();
		for(int i=0;i<account_names.size();i++){
			String name=input[0][1];

			if(account_names.get(i).getText().equalsIgnoreCase(name)){
				account_names.get(i).click();

			}

		}
	}

}











