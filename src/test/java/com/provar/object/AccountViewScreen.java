package com.provar.object;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterSuite;
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
	@FindBy (xpath="//input[@id='acc10']") public WebElement Edit_Phone;
	@FindBy (xpath="//input[@id='acc11']") public WebElement Edit_Fax;
	@FindBy (xpath="//input[@value=' Save ']") public WebElement Save;
	@FindBy (xpath="//div[@id='acc8_ileinner']") public WebElement Annual_Revenue;
	@FindBy (xpath="//input[@id='acc8']") public WebElement Edit_Annual_Revenue;
	@FindBy (xpath="//input[@value='Attach File']") public WebElement NotesAttachments_AttachFile;
	@FindBy (xpath="//input[@id='file']") public WebElement NotesAttachments_ChooseFile;
	@FindBy (xpath="//input[@value=' Done ']") public WebElement NotesAttachments_ChooseFile_DoneButton;

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

	public void update_assert(String NewPhoneNo, String Fax){

		edit_button.click();
		Edit_Phone.clear();
		Edit_Phone.sendKeys(NewPhoneNo);
		if(Edit_Fax.getText().equalsIgnoreCase(Fax)){
			System.out.println("Pass");
		}else{
			System.out.println("Fail");
		}
		Save.click();
	}

	public void inline_edit_assert(String annual_revenue) throws InterruptedException{
		Actions action=new Actions(driver);
		action.doubleClick(Annual_Revenue).build().perform();
		Thread.sleep(3000);
		Edit_Annual_Revenue.clear();
		Thread.sleep(3000);
		Edit_Annual_Revenue.sendKeys(annual_revenue);
		Thread.sleep(3000);
		Save.click();
		Annual_Revenue.getText().equalsIgnoreCase(annual_revenue);
	}
	
	public void ChooseFile(String filePath) throws InterruptedException{
		
		NotesAttachments_AttachFile.click();
		NotesAttachments_ChooseFile.sendKeys(filePath);
		NotesAttachments_ChooseFile_DoneButton.click();
	}
	
	
	
	
}
