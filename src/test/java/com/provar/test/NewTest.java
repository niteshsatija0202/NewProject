package com.provar.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NewTest {
 
static	WebDriver driver;
	
	@Test
	public static void test1() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","/POM/drivers/win/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("admin1@test1.provar.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Provar01");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		driver.findElement(By.xpath("//li[@id='Case_Tab']")).click();
		driver.findElement(By.xpath("//a[text()='00001082']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value=' Edit ']")).click();
		//driver.findElement(By.xpath("//input[@value=' New ']")).click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		//Thread.sleep(3000);
		WebElement a1=driver.findElement(By.xpath("//tr[@class='multiSelectPicklistRow']//td[1]//select"));
		System.out.println(a1.getText());
	    WebElement a2=	driver.findElement(By.xpath("//tr[@class='multiSelectPicklistRow']//td[3]//select"));
	    System.out.println(a2.getText() +"null");
		
		
		
	}
	
}
