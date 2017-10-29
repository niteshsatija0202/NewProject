package com.provar.object;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AccountPage_LT {
	WebDriver driver;


	public AccountPage_LT(WebDriver driver){
		this.driver=driver;
	}
	@FindBy (xpath="//div[@class='slds-icon-waffle']") public WebElement app_launcher;
	@FindBy (xpath="//span[@class='label-ctr slds-app-launcher__tile-body slds-app-launcher__tile-body--small']//span[text()='Accounts']") public WebElement app_account_tab; 

	@FindBy (xpath="//a[@title='Accounts']") public WebElement account_tab;

	@FindBy (xpath="//a[@title='New']") public WebElement create_new;
	@FindBy (xpath="//button[@title='Cancel']") public WebElement cancel;
	@FindBy (xpath="//button[@title='Save & New']") public WebElement save_new;
	@FindBy (xpath="//button[@title='Save']") public WebElement save;

	@FindBy (xpath="//span[@class='triggerLinkText selectedListView uiOutputText']") public WebElement search_type;
	@FindBy (xpath="//ul[@class='slds-dropdown__list slds-show']//li//a") public List<WebElement> search_records;

	@FindBy (xpath="//div[@class='desktop forcePageError']") public WebElement page_error;
	@FindBy (xpath="//li[@class='form-element__help']") public WebElement account_name_error; 

	@FindBy (xpath="//input[@class='input uiInput uiInputText uiInput--default uiInput--input']") public WebElement account_name;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[2]/div[2]//input") public WebElement phone;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[3]/div[2]//input") public WebElement fax;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[4]/div[1]//input") public WebElement account_number;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[4]/div[2]//input") public WebElement website;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[5]/div[1]//input") public WebElement account_site;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[5]/div[2]//input") public WebElement ticker_symbol;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[1]/div[2]//a") public WebElement rating;
	@FindBy (xpath="//ul[@class='scrollable']/li") public List<WebElement> rating_list;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[6]/div[1]//a") public WebElement type;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[6]/div[2]//a") public WebElement ownership;
	@FindBy (xpath="//div[@class='slds-form slds-is-editing']/div[7]/div[1]//a") public WebElement industry;
	@FindBy (xpath="//div[@class='form-element']") public WebElement sla_exp_date;
	@FindBy (xpath="//input[@class='default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup']") public WebElement parent_lookup;

	@FindBy (xpath="//div[@class='form-element__group']//textarea") public WebElement billing_street;
	@FindBy (xpath="//div[@class='form-element__group']//input[@class='compoundBorderRight compoundBorderBottom input']") public WebElement billing_city;



	public void searchtype(String record_name) throws InterruptedException{
		Thread.sleep(5000);
		app_launcher.click();
		app_account_tab.click();
		search_type.click();
		List<WebElement> str=search_records;
		for(WebElement str1:str ){
			if(str1.getText().equalsIgnoreCase(record_name)){
				str1.click();
				break;
			}
		}
	}

	public void datepicker(String date ,String month ,String year) throws InterruptedException{

		sla_exp_date.click();
		Thread.sleep(5000);
		for(int i=0;i<11;i++){
			if(driver.findElement(By.xpath("//h2[@class='monthYear']")).getText().equalsIgnoreCase(month)){
				System.out.println(driver.findElement(By.xpath("//h2[@class='monthYear']")).getText());
				new Select (driver.findElement(By.xpath("//select[@class='slds-select picklist__label']"))).selectByValue(year);
				List<WebElement> str=driver.findElements(By.xpath("//td[@class='uiDayInMonthCell']"));
				for(WebElement str1:str){
					if(str1.getText().equalsIgnoreCase(date)){
						str1.click();
						break;
					}	
				}break;

			}else{
				driver.findElement(By.xpath("//a[@title='Go to next month']")).click();
			}	
		}	
	}


	public void parentLookup(String searchText , String accountName){

		parent_lookup.sendKeys(searchText);
		driver.findElement(By.xpath("//span[@title='\""+searchText+"\" in Accounts']")).click();
		driver.findElement(By.xpath("//table[@class='forceRecordLayout slds-table slds-table_cell-buffer slds-table_bordered uiVirtualDataGrid--default uiVirtualDataGrid']//a[@title='"+accountName+"']")).click();

	}

	public void account_name_error(String error_message) throws InterruptedException{
		Thread.sleep(5000);
		app_launcher.click();
		app_account_tab.click();
		create_new.click();
		save.click();
		String error=account_name_error.getText();

		if(error.equals(error_message)){
			System.out.println("pass");
		}else{
			System.out.println("fail");
		}
	}

	public void page_error(String error) throws InterruptedException{
		Thread.sleep(5000);
		app_launcher.click();
		app_account_tab.click();
		create_new.click();
		save.click();
		String pageError=page_error.getText();
		if(pageError.contains(error)){
			System.out.println("pass");
		}else{
			System.out.println("fail");
		}
	}


	public String unique_name(int n){
		UUID uuid=UUID.randomUUID();
		String str=uuid.toString();
		String randomuuid=str.replace("-", "");

		String random_name="";
		for(int i=0;i<n;i++){
			random_name=random_name+randomuuid.charAt(i);

		}
		return random_name;
	}


	public void create_account(String ratingValue) throws InterruptedException, AWTException{
		Thread.sleep(5000);
		app_launcher.click();
		app_account_tab.click();
		create_new.click();


		rating.click();
		List<WebElement> str=rating_list;
		for(WebElement str1:str){
			if    ( str1.getText().equalsIgnoreCase(ratingValue)){
				str1.click();
				break;
			}
		}

		account_name.sendKeys(unique_name(5));

		phone.sendKeys("987654321");
		fax.sendKeys("576");
		account_number.sendKeys("56967988");
		website.sendKeys("www.google.com");
		account_site.sendKeys("www.provar.com");
		ticker_symbol.sendKeys("g232hj5");


		parentLookup("Test", "TestAccount1");
		Thread.sleep(3000);
		datepicker("27","December","2018");
		save.click();




	}
}





