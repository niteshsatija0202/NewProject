package com.provar.test;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.LoggingMXBean;

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
import com.provar.object.SalesforceHome;
import com.provar.object.Vf_Only;
import com.provar.utilities.CaptureScreenShot;
import com.provar.utilities.ChooseBrowser;
import com.provar.utilities.ReadExcel;
import com.provar.utilities.ReadPropertyFile;
import com.provar.utilities.WriteExcel;


public class AccountTest {
	WebDriver driver;
	LoginPage login;
	AccountPage account;
	AccountViewScreen  accountView;
	AccountListViewScreen accountList;
	Vf_Only vfOnly;
	SalesforceHome SfHome;





	@BeforeMethod
	public void launchbrowser() throws Throwable{

		ChooseBrowser chooseBrowser = new ChooseBrowser();
		driver = chooseBrowser.getBrowser();


		SfHome=PageFactory.initElements(driver, SalesforceHome.class);
		login=PageFactory.initElements(driver, LoginPage.class);
		account=PageFactory.initElements(driver, AccountPage.class);
		accountView=PageFactory.initElements(driver, AccountViewScreen.class);
		accountList=PageFactory.initElements(driver, AccountListViewScreen.class);
		vfOnly=PageFactory.initElements(driver, Vf_Only.class);
		
		CaptureScreenShot.driver=driver;

		login.login(ReadPropertyFile.getProperty().get("Username"), ReadPropertyFile.getProperty().get("Password"));
		String currentUrl=driver.getCurrentUrl();
		if(currentUrl.contains("ap5.lightning.force.com")){
			SfHome.classicSwitching();

		}

	}


	@Test (description="Error_assertion")
	public void Error_assertion() throws Throwable{
		account.account_name_error("Error: You must enter a value");
		

	}

	@Test (description="pageError")
	public void pageError() throws Throwable{
		account.page_error("Review all error messages below to correct your data.");
		

	}

	@Test (description="createAccount")
	public void createAccount() throws Throwable {
		account.create_account();
		String obj_name=accountView.Object_name();
		System.out.println("Object Name : "+obj_name);
		String obj_id=accountView.object_id();
		System.out.println("Object Id : "+obj_id);
		ArrayList list=new ArrayList();
		list.add(obj_id);
		list.add(obj_name);
		WriteExcel.Write(list,ReadPropertyFile.getProperty().get("ReadExcelFile"),ReadPropertyFile.getProperty().get("ReadExcelFile_Sheet"));
	}

	@Test(dependsOnMethods = { "createAccount" },description="selectRecord")
	public void selectRecord() throws Throwable{
		account.SearchType("All Accounts");
		String[][] input =ReadExcel.read(ReadPropertyFile.getProperty().get("ReadExcelFile"),ReadPropertyFile.getProperty().get("ReadExcelFile_Sheet"));
		accountList.selectRecord(input[1][0]);
	}

	@Test(dependsOnMethods = { "createAccount" })
	public void UpdateAndAssertRecord() throws Throwable{
		account.SearchType("All Accounts");
		String[][] input =ReadExcel.read(ReadPropertyFile.getProperty().get("ReadExcelFile"),ReadPropertyFile.getProperty().get("ReadExcelFile_Sheet"));
		accountList.selectRecord(input[1][0]);
		accountView.update_assert("987654321", "678576");
	}

	@Test(dependsOnMethods = { "createAccount" })
	public void InlineAndAssert() throws Throwable{
		account.SearchType("All Accounts");
		String[][] input =ReadExcel.read(ReadPropertyFile.getProperty().get("ReadExcelFile"),ReadPropertyFile.getProperty().get("ReadExcelFile_Sheet"));
		accountList.selectRecord(input[1][0]);
		accountView.inline_edit_assert("265000");
	}

	@Test(dependsOnMethods = { "createAccount" })
	public void uploadFile() throws Throwable{
		account.SearchType("All Accounts");
		String[][] input =ReadExcel.read(ReadPropertyFile.getProperty().get("ReadExcelFile"),ReadPropertyFile.getProperty().get("ReadExcelFile_Sheet"));
		accountList.selectRecord(input[1][0]);
		accountView.ChooseFile("D:\\POM\\resources\\TestDocument.txt");

	}

	@Test
	public void vfMapping() throws Throwable{
		vfOnly.vf_mapping("Test1", "Test2");
	}
	@AfterMethod
	public void close_browser(){
		driver.quit();
	}
}
