package com.hms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hms.fileHandle.ExcelFileReader;

public class SearchPatient {
	private WebDriver driver;
	private ExcelFileReader fileReader;

	@FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[10]/a/div/div[2]")
	WebElement srchPatient;
	
	@FindBy(how=How.ID,using="searchdata")
	WebElement srchBox;
	
	@FindBy(how=How.ID,using="submit")
	WebElement srchBttn;
	
	@FindBy(how=How.CSS,using=".mainTitle")
	WebElement mainTitle;
	
	public SearchPatient(WebDriver driver){
		this.driver=driver; 
		PageFactory.initElements(driver, this);
		fileReader=new ExcelFileReader();
	}
	
	public void searchName() {
		srchPatient.click();
		String mtitle =this.main_title(); 
		Assert.assertTrue(mtitle.equals("ADMIN | VIEW PATIENTS"));
		srchBox.sendKeys(fileReader.readExcel("Admin_searchPatient",1,0));
		srchBttn.click();
	}
	
	
	public String main_title() {
		return mainTitle.getText();
	}
	
}
