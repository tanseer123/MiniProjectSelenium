package com.hms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hms.fileHandle.PropertyReader;

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
	
	@FindBy(how=How.CSS,using="span[style='color:red;']")
	WebElement invalidMsg;
	
	
	//private By titleText =By.className("barone");
	
	
	private String userName=PropertyReader.getProperty("adminUserName");
	private String pass=PropertyReader.getProperty("adminPass");
	
	
   
    public Admin(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        
    }

	public void loginAdmin() {
		userField.sendKeys(userName);
		passwordField.sendKeys(pass);
		login.click();   
		String loginUserName=this.getLoginUserName();
		Assert.assertTrue(loginUserName.equals("Admin"));
	}
	public String getLoginUserName() {
		
		return loggedInAs.getText();
		
	}

	public void invalidLoginAdmin() {
		adminClick.click();
		userField.sendKeys(PropertyReader.getProperty("invalidUsername"));
		passwordField.sendKeys(PropertyReader.getProperty("invalidPassword"));
		login.click();   
		Assert.assertTrue(invalidMsg.getText().equals("Invalid username or password"));
		
	}
	
	
	
}
