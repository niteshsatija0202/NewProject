package com.provar.object;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class AccountViewScreen {
 
	WebDriver driver;
	public  AccountViewScreen(WebDriver driver) {
	   this.driver=driver;
	}
	
	@FindBy (xpath="//input[@value=' Edit ']") public WebElement edit_button;
	@FindBy (xpath="//input[@value='Delete']") public WebElement delete_button;
	@FindBy (xpath="//input[@value='Include Offline']") public WebElement includeOffline_button;
	@FindBy (xpath="//div[@id='acc2_ileinner']") public WebElement account_name;
	@FindBy (xpath="//div[@id='acc10_ileinner']") public WebElement phone;
	
	
	
	@Test

	public String Object_name(){
		String str1=account_name.getText();
		String[] str2=str1.split(" ");
		String Object_name= str2[0];
		return Object_name;
		
	}
	
	
	
	@Test
  public String object_id() {
		
		String CurrentUrl=driver.getCurrentUrl();
		String[] str=CurrentUrl.split("/");
		String object_id=str[3];
		return object_id;
  }
}
