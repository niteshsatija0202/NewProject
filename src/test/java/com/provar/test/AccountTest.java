package com.provar.test;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.provar.object.AccountListViewScreen;
import com.provar.object.AccountPage;
import com.provar.object.AccountViewScreen;
import com.provar.object.LoginPage;
import com.provar.utilities.ChooseBrowser;
import com.provar.utilities.ReadPropertyFile;
import com.provar.utilities.WriteExcel;


public class AccountTest {
	WebDriver driver;
	LoginPage login;
	AccountPage account;
	AccountViewScreen  accountView;
	AccountListViewScreen accountList;

	@BeforeMethod
	public void launchbrowser() throws Throwable{

		ChooseBrowser chooseBrowser = new ChooseBrowser();
		driver = chooseBrowser.getBrowser();

		login=PageFactory.initElements(driver, LoginPage.class);
		account=PageFactory.initElements(driver, AccountPage.class);
		accountView=PageFactory.initElements(driver, AccountViewScreen.class);
		accountList=PageFactory.initElements(driver, AccountListViewScreen.class);
	}



	@Test
	public void Error_assertion() throws Throwable{
		login.login(ReadPropertyFile.getProperty().get("Username"), ReadPropertyFile.getProperty().get("Password"));
		account.account_name_error("Error: You must enter a value");

	}

	@Test
	public void pageError() throws Throwable{
		login.login(ReadPropertyFile.getProperty().get("Username"), ReadPropertyFile.getProperty().get("Password"));
		account.page_error("Review all error messages below to correct your data.");

	}
	@Test
	public void createAccount() throws Throwable {
		login.login(ReadPropertyFile.getProperty().get("Username"), ReadPropertyFile.getProperty().get("Password"));
		account.create_account();
		String obj_name=accountView.Object_name();
		String obj_id=accountView.object_id();
		ArrayList list=new ArrayList();
		list.add(obj_id);
		list.add(obj_name);
		WriteExcel.Write(list);
	}
    @Test
	public void selectRecord() throws Throwable{
		login.login(ReadPropertyFile.getProperty().get("Username"), ReadPropertyFile.getProperty().get("Password"));
		account.SearchType("All Accounts");
		accountList.selectRecord("41e89e");
	}



	@AfterMethod
	public void close_browser(){
		driver.quit();
	}
}
