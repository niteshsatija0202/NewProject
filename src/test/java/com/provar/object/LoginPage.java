package com.provar.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.provar.utilities.ChooseBrowser;

public class LoginPage {
  
  WebDriver driver;
	public LoginPage(WebDriver driver){
		this.driver=driver;
		
	}
	
	
	@FindBy (xpath="//input[@type='email']") public WebElement email;
	@FindBy (xpath="//input[@type='password']") public WebElement password1;
	@FindBy (xpath="//input[@id='Login']") public WebElement login;
	@FindBy (xpath="//input[@type='checkbox']") public WebElement remember_me;
	
	
	public void login(String username,String password){
                   email.sendKeys(username);
                   password1.sendKeys(password);
                   login.click();
	}
	
	
  }

