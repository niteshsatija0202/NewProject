package com.provar.test;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.provar.object.AccountListViewScreen;
import com.provar.object.AccountListViewScreen_LT;
import com.provar.object.AccountPage;
import com.provar.object.AccountPage_LT;
import com.provar.object.AccountViewScreen;
import com.provar.object.AccountViewScreen_LT;
import com.provar.object.LoginPage;
import com.provar.object.SalesforceHome;
import com.provar.object.Vf_Only;
import com.provar.object.Vf_Only_LT;
import com.provar.utilities.CaptureScreenShot;
import com.provar.utilities.ChooseBrowser;
import com.provar.utilities.ReadExcel;
import com.provar.utilities.ReadPropertyFile;
import com.provar.utilities.WriteExcel;

public class AccountTestLT {

	WebDriver driver;
	LoginPage login;
	SalesforceHome SfHome;
    AccountPage_LT account;
    AccountViewScreen_LT account_view;
    AccountListViewScreen_LT account_list;
    Vf_Only_LT vf_only;

	@BeforeMethod
	public void launchbrowser() throws Throwable{

		ChooseBrowser chooseBrowser = new ChooseBrowser();
		driver = chooseBrowser.getBrowser();

		login=PageFactory.initElements(driver, LoginPage.class);
		account=PageFactory.initElements(driver, AccountPage_LT.class);
		account_view=PageFactory.initElements(driver, AccountViewScreen_LT.class);
		account_list=PageFactory.initElements(driver, AccountListViewScreen_LT.class);
		vf_only=PageFactory.initElements(driver,Vf_Only_LT.class);
		SfHome=PageFactory.initElements(driver, SalesforceHome.class);
       

		CaptureScreenShot.driver=driver;

		login.login(ReadPropertyFile.getProperty().get("Username"), ReadPropertyFile.getProperty().get("Password"));
		Thread.sleep(5000);
		String currentUrl=driver.getCurrentUrl();
		
		if(currentUrl.contains("ap5.salesforce.com")){
			SfHome.lightningSwitching();

		}

	}
	@Test 
	public void Error_assertion() throws Throwable{
		account.account_name_error("Complete this field");

	}

	@Test 
	public void pageError() throws Throwable{
		account.page_error("Review the errors on this page.");
	}
	@Test
	public void createAccount() throws Throwable {
		account.create_account("Hot");
		String str1=account_view.Object_name();
		System.out.println("Object Name : "+str1);
		String str=account_view.object_id();
		System.out.println("Object Id : "+ str);
		ArrayList list=new ArrayList();
		list.add(str);
		list.add(str1);
		list.add("Pass");
		WriteExcel.Write(list,ReadPropertyFile.getProperty().get("ReadExcelFileLT"),ReadPropertyFile.getProperty().get("ReadExcelFileLT_Sheet"));
	}
	
	
	@Test (dependsOnMethods = { "createAccount" })
	public void selectRecord() throws Throwable{
		account.searchtype("All Accounts");
		String[][] input =ReadExcel.read(ReadPropertyFile.getProperty().get("ReadExcelFileLT"),ReadPropertyFile.getProperty().get("ReadExcelFileLT_Sheet"));
		account_list.selectRecord(input[1][0]);
	}
	
	@Test (dependsOnMethods = { "createAccount" })
	public void UpdateAndAssertRecord() throws Throwable{
		account.searchtype("All Accounts");
		String[][] input =ReadExcel.read(ReadPropertyFile.getProperty().get("ReadExcelFileLT"),ReadPropertyFile.getProperty().get("ReadExcelFileLT_Sheet"));
		account_list.selectRecord(input[1][0]);
		account_view.update_assert("987654321", "576");
	}
	@Test (dependsOnMethods = { "createAccount" })
	public void uploadFile() throws Throwable{
		account.searchtype("All Accounts");
		String[][] input =ReadExcel.read(ReadPropertyFile.getProperty().get("ReadExcelFileLT"),ReadPropertyFile.getProperty().get("ReadExcelFileLT_Sheet"));
		account_list.selectRecord(input[1][0]);
		account_view.testUpload("D:\\POM\\resources\\TestDocument.txt");
	}
	@Test
	public void vfMapping() throws Throwable{
		Thread.sleep(5000);
		account.app_launcher.click();
		vf_only.vf_mapping("Test1", "Test2");
	}
	@AfterMethod
	public void close_browser(){
		driver.quit();
	}

}
