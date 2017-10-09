package com.provar.object;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class AccountListViewScreen {

	WebDriver driver;

	public AccountListViewScreen(WebDriver driver) {
		this.driver=driver;
	}

	@FindBy (xpath="//input[@value='New Account']") public WebElement new_account;
	@FindBy (xpath="//a[text()='Edit']") public WebElement edit_view;
	@FindBy (xpath="//a[text()='Delete']") public WebElement delete_view;
	@FindBy (xpath="//a[text()='Create New View']") public WebElement create_new_view;
	@FindBy (xpath="//div[@class='x-grid3-cell-inner x-grid3-col-Name']/a") public List<WebElement> account_names;




	@Test
	public void selectRecord(String record_name){
		List<WebElement> names= account_names;

		for(int i=0;i<account_names.size();i++){
			

			if(account_names.get(i).getText().equalsIgnoreCase(record_name)){
				account_names.get(i).click();

			}
		}
	}

}
