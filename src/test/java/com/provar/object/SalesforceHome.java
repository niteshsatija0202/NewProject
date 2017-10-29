package com.provar.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class SalesforceHome {
	WebDriver driver;
	
	public SalesforceHome(WebDriver driver){
		this.driver=driver;
		
	}
	
	@FindBy (xpath=("//div[@id='userNavButton']")) public WebElement userButton; 
	@FindBy (xpath=("//a[text()='Switch to Lightning Experience']")) public WebElement switchngButton;
	@FindBy (xpath=("//button[@class='bare slds-button uiButton forceHeaderButton oneUserProfileCardTrigger']")) public WebElement userButton_LT;
	@FindBy (xpath=("//a[text()='Switch to Salesforce Classic']")) public WebElement switchngButton_LT;
	
	
	public void lightningSwitching(){
		userButton.click();
		switchngButton.click();
	}
	
	public void classicSwitching(){
		userButton_LT.click();
		switchngButton_LT.click();
	}
	
  
}
