package com.provar.object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class AccountListViewScreen_LT {
	WebDriver driver;

	public AccountListViewScreen_LT(WebDriver driver) {
		this.driver=driver;
	}

	
	
   @FindBy (xpath="//tbody//th//a") public List<WebElement> account_names;




	

	public void selectRecord(String record_name) throws InterruptedException{
		Thread.sleep(4000);
		List<WebElement> names= account_names;

		for(int i=0;i<account_names.size();i++){

			if(account_names.get(i).getText().equalsIgnoreCase(record_name)){
				account_names.get(i).click();
                break;
			}

		}
	}
}
