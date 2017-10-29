package com.provar.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class Vf_Only {
	
	WebDriver driver;
	 public Vf_Only(WebDriver driver){
		 this.driver=driver;
	 }
	@FindBy (xpath="//li[@class='zen-moreTabs zen-lastItem']") public WebElement tab_dropdown;
	@FindBy (xpath="//li[@id='01r7F000000cgXq_Tab']") public WebElement vfOnly_tab; 
	@FindBy (xpath="//input[@name='j_id0:j_id1:j_id2:j_id8:j_id10']") public WebElement first_name;
	@FindBy (xpath="//input[@name='j_id0:j_id1:j_id2:j_id8:j_id12']") public WebElement last_name;
	@FindBy (xpath="//input[@name='j_id0:j_id1:j_id2:j_id8:j_id14']") public WebElement gender_male;
	@FindBy (xpath="//input[@id='j_id0:j_id1:j_id2:j_id28:j_id29']']") public WebElement Test;
	@FindBy (xpath="//div[@id='j_id0:j_id1:j_id2:j_id49']//td[@class='dataCol  first  last ']//tr[2]//td[3]") public WebElement html_phoneNo;
	@FindBy (xpath="//input[@name='j_id0:j_id1:j_id2:j_id3:j_id4']") public WebElement save;
	@FindBy (xpath="j_id0:j_id1:j_id2:j_id3:j_id5") public WebElement cancel;
  @Test
  public void vf_mapping(String FirstName,String SecondName) {
	  tab_dropdown.click();
	  vfOnly_tab.click();
	  first_name.sendKeys(FirstName);
	  last_name.sendKeys(SecondName);
	  gender_male.click();
	  System.out.println("Phone Number in html table is : "+html_phoneNo.getText());
	  save.click();
  }
}
