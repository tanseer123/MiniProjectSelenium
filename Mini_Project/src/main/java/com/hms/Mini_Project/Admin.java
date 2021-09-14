package com.hms.Mini_Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.fileHandle.PropertyReader;

public class Admin {

	WebDriver driver;
	
	@FindBy(how=How.CSS,using="a[href='hms/admin']")
	WebElement adminClick;
	
	@FindBy(how=How.NAME,using="username")
	WebElement userField;
	
	@FindBy(how=How.NAME,using="password")
	WebElement passwordField;
	
	@FindBy(how=How.XPATH,using="//button[@name='submit']")
	WebElement login;
	
	@FindBy(how=How.CSS,using=".username")
	WebElement loggedInAs;
	
	
	//private By titleText =By.className("barone");
	
	
	private String userName=PropertyReader.getProperty("adminUserName");
	private String pass=PropertyReader.getProperty("adminPass");
	
	
   
    public Admin(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        
    }

	public void loginAdmin() {
		adminClick.click();
		userField.sendKeys(userName);
		passwordField.sendKeys(pass);
		login.click();   
	}
	public String getLoginUserName() {
		
		return loggedInAs.getText();
		
	}
	
	
	
}
