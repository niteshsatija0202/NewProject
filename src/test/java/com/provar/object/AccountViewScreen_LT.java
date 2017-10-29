package com.provar.object;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;



public class AccountViewScreen_LT {
	WebDriver driver;
	public  AccountViewScreen_LT(WebDriver driver) {
		this.driver=driver;
	}


	@FindBy (xpath="//span[text()='Related']") public WebElement related;
	@FindBy (xpath="//span[text()='Details']") public WebElement details;
	@FindBy (xpath="//div[@class='slds-form ']/div[2]/div[1]//span[@class='test-id__field-value slds-form-element__static slds-grow']//span") public WebElement account_name;
	@FindBy (xpath="//div[@class='slds-form ']/div[2]/div[2]//span[@dir='ltr']") public WebElement phone;
	@FindBy (xpath="//div[@class='slds-form ']/div[3]/div[2]//span[@dir='ltr']") public WebElement fax; 
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[2]/div[2]//input") public WebElement edit_phone;
	@FindBy (xpath="//button[@class='slds-button slds-button--neutral uiButton--brand uiButton forceActionButton']//span[text()='Save']") public WebElement save; 
	@FindBy (xpath="//div[@title='Upload Files']") public WebElement upload; 

	@FindBy (xpath="//div[@title='Upload Files']") public WebElement upload_file; 
	@FindBy (xpath="//button[@class='slds-button slds-button--neutral ok uiButton--default uiButton--brand uiButton']") public WebElement done; 
	


	public String Object_name(){
		details.click();
		String Object_name=account_name.getText();
		return Object_name;

	}


	public String object_id() {
		String CurrentUrl=driver.getCurrentUrl();
		String[] str=CurrentUrl.split("/");
		String object_id=str[6];
		return object_id;
	}

	public void update_assert(String NewPhoneNo, String Fax) throws InterruptedException{
		details.click();
		Thread.sleep(4000);
		if(fax.getText().equalsIgnoreCase(Fax)){
			System.out.println("Pass");
		}else{
			System.out.println("Fail");
		}
		Actions action=new Actions(driver);
		action.doubleClick(phone).build().perform();
		Thread.sleep(4000);
		edit_phone.clear();
		edit_phone.sendKeys(NewPhoneNo);
		save.click();

	}
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void uploadFile(String fileLocation) {
		try {
			//Setting clipboard with file location
			setClipboardData(fileLocation);
			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	public void testUpload(String filepath) throws InterruptedException
	{
		related.click();

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)", "");
		Thread.sleep(4000);
		upload_file.click();
		Thread.sleep(4000);
		uploadFile(filepath);
		Thread.sleep(2000);
		done.click();
	}

}
