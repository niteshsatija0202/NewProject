package com.provar.object;

import static org.testng.Assert.assertTrue;

import java.util.Set;
import java.util.UUID;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.provar.utilities.ChooseBrowser;

public class AccountPage {

	WebDriver driver;
	public AccountPage(WebDriver driver){
		this.driver=driver;

	}


	//-------------------Account_information----------------------------------------

	@FindBy (xpath="//div[@id='errorDiv_ep']") public WebElement page_error;

	@FindBy (xpath="//a[@title='Accounts Tab']") public WebElement account_tab;
	@FindBy (xpath="//select[@id='fcf']") public WebElement search_type;
	@FindBy (xpath="//select[@id='fcf']/option[@selected]") WebElement  selected_type; 
	@FindBy (xpath="//input[@value=' Go! ']") public WebElement go_button;
	@FindBy (xpath="//input[@value=' New ']") public WebElement create_new;
	@FindBy (xpath="//input[@value=' Save ']") public WebElement save;
	@FindBy (xpath="//input[@value='Save & New']") public WebElement save_new;
	@FindBy (xpath="//input[@value='Cancel']") public WebElement cancel;
	@FindBy (xpath="//input[@id='acc2']") public WebElement account_name;
	@FindBy (xpath="//div[@class='errorMsg']") public WebElement account_name_error;
	@FindBy (xpath="//select[@id='acc9']") public WebElement rating;
	@FindBy (xpath="//input[@id='acc10']") public WebElement phone ;
	@FindBy (xpath="//input[@id='acc3']") public WebElement parent_account ;
	@FindBy (xpath="//input[@id='acc11']") public WebElement fax ;
	@FindBy (xpath="//img[@alt='Parent Account Lookup (New Window)']") public WebElement parent_account_lookup ;
	@FindBy (xpath="//input[@id='acc5']") public WebElement account_number ;
	@FindBy (xpath="//input[@id='acc12']") public WebElement website ;
	@FindBy (xpath="//input[@id='acc23']") public WebElement account_site ;
	@FindBy (xpath="//input[@id='acc13']") public WebElement ticker_symbol ;
	@FindBy (xpath="//select[@id='acc6']") public WebElement type ;
	@FindBy (xpath="//select[@id='acc14']") public WebElement ownership ;
	@FindBy (xpath="//select[@id='acc7']") public WebElement industry;
	@FindBy (xpath="//input[@id='acc15']") public WebElement employess ;
	@FindBy (xpath="//input[@id='acc8']") public WebElement annual_revenue ;
	@FindBy (xpath="//input[@id='acc16']") public WebElement sic_code ;

	//------------------------------------Address_information------------------------------

	@FindBy (xpath="//textarea[@id='acc17street']")public  WebElement billing_street ;
	@FindBy (xpath="//textarea[@id='acc18street']") public WebElement shipping_street ;
	@FindBy (xpath="//input[@id='acc17city']")public  WebElement billing_city ;
	@FindBy (xpath="//input[@id='acc18city']") public WebElement shipping_city ;
	@FindBy (xpath="//input[@id='acc17state']") public WebElement billing_state;
	@FindBy (xpath="//input[@id='acc18state']") public WebElement shipping_state;
	@FindBy (xpath="//input[@id='acc17zip']") public WebElement billing_zip;
	@FindBy (xpath="//input[@id='acc18zip']")public  WebElement shipping_zip;
	@FindBy (xpath="//input[@id='acc17country']") public WebElement billing_country;
	@FindBy (xpath="//input[@id='acc18country']")public  WebElement shipping_country;

	//-----------------------------------Additional_information--------------------------------

	@FindBy (xpath="//select[@id='00N7F0000061ODV']")public  WebElement customer_priority ;
	@FindBy (xpath="//select[@id='00N7F0000061ODX']") public WebElement sla;
	@FindBy (xpath="//input[@id='00N7F0000061ODZ']")public  WebElement sla_serial_number;
	@FindBy (xpath="//input[@id='00N7F0000061ODW']") public WebElement number_of_locations;
	@FindBy (xpath="//select[@id='00N7F0000061ODa']") public WebElement upsell_opportunity;
	@FindBy (xpath="//select[@id='00N7F0000061ODU']")public  WebElement  active;
	@FindBy (xpath="//input[@id='00N7F0000061ODY']")public  WebElement sla_date;
	@FindBy (xpath="//span[@class='dateFormat']") public WebElement sla_SF_date;

	@FindBy (xpath="//td[@class='last data2Col']/textarea") public WebElement description;







	public void SearchType(String search){
		account_tab.click();
		String selected=selected_type.getText();

		if (selected.equals(search)){
			go_button.click();

		}else{
			new Select(search_type).selectByVisibleText(search);

		}
	}

	public void account_name_error(String error_message){

		account_tab.click();
		create_new.click();
		save.click();
		String error=account_name_error.getText();

	}

	public void page_error(String error){
		account_tab.click();
		create_new.click();
		save.click();
		String pageError=page_error.getText();

	}

	public void create_account(){
		account_tab.click();
		create_new.click();
		account_name.sendKeys(unique_name(6));
		new Select(rating).selectByValue("Hot");
		phone.sendKeys("1234567890");
		fax.sendKeys("678576");
		account_number.sendKeys("544676");
		website.sendKeys("www.google.com");
		account_site.sendKeys("test");
		ticker_symbol.sendKeys("ghfhg");
		new Select(type).selectByIndex(2);
		new Select(ownership).selectByValue("Public");
		annual_revenue.sendKeys("125000");
		sic_code.sendKeys("1234"); 
		new Select(industry).selectByIndex(1);
		employess.sendKeys("30667");

		billing_street.sendKeys("MG Road");
		billing_city.sendKeys("Gurgaon");
		billing_state.sendKeys("Haryana");
		billing_zip.sendKeys("122002");
		billing_country.sendKeys("India");
		shipping_street.sendKeys("MG Road");
		shipping_city.sendKeys("Gurgaon");
		shipping_state.sendKeys("Haryana");
		shipping_zip.sendKeys("122002");
		shipping_country.sendKeys("India");

		new Select(customer_priority).selectByValue("Low");
		sla_SF_date.click();
		number_of_locations.sendKeys("10");
		new Select(active).selectByIndex(2);
		new Select(sla).selectByIndex(1);
		new Select(upsell_opportunity).selectByIndex(3);

		description.sendKeys("Test Account");
		parent_lookup();
		save.click();
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


	public void parent_lookup(){
		parent_account_lookup.click();
		String parent=driver.getWindowHandle();
		Set<String> str=driver.getWindowHandles();
		String lookup="";
		for(String child:str){
			lookup=child;	
		}
		driver.switchTo().window(lookup);
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@title='Results']")));
		driver.findElement(By.xpath("//a[text()='Ab12']")).click();
		driver.switchTo().window(parent);

	}

}


