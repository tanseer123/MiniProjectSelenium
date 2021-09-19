package com.hms.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.hms.fileHandle.ExcelFileReader;
import com.hms.fileHandle.PropertyReader;

public class PatientLogin {
	
	WebDriver driver;
	
	@FindBy(how=How.CSS,using="a[href='hms/user-login.php']")
	WebElement patientClick;
	
	@FindBy(how=How.NAME,using="username")
	WebElement userField;
	
	@FindBy(how=How.NAME,using="password")
	WebElement passwordField;
	
	@FindBy(how=How.XPATH,using="//button[@name='submit']")
	WebElement login;
	
	@FindBy(how=How.CSS,using=".username")
	WebElement loggedInAs;
	
	@FindBy(how=How.LINK_TEXT,using="Create an account")
	WebElement clickCreateAcc;
	
	@FindBy(how=How.NAME,using="full_name")
	WebElement name;
	
	@FindBy(how=How.NAME,using="address")
	WebElement address;
	
	@FindBy(how=How.CSS,using="input[name='city']")
	WebElement city;

	@FindBy(how=How.CSS,using="#registration > fieldset > div:nth-child(6) > div > label:nth-child(2)")
	WebElement female;
	
	@FindBy(how=How.CSS,using="#registration > fieldset > div:nth-child(6) > div > label:nth-child(4)")
	WebElement male;
	
	
	@FindBy(how=How.NAME,using="email")
	WebElement email;
	
	@FindBy(how=How.XPATH,using="//*[@id='user-availability-status1']/span")
	WebElement email_available;
	
	@FindBy(how=How.NAME,using="password")
	WebElement patient_pass;
	
	@FindBy(how=How.NAME,using="password_again")
	WebElement patient_confirmPass;
	
	@FindBy(how=How.ID,using="submit")
	WebElement doc_submitBtn;
	
	@FindBy(how=How.CSS,using="span[style='color:red;']")
	WebElement invalidMsg;

	private String userName=PropertyReader.getProperty("patientUserName");
	private String pass=PropertyReader.getProperty("patientPassword");
	private ExcelFileReader fileReader;
	
	public PatientLogin(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		fileReader=new ExcelFileReader();
		}
	
	public void patientLogin(){
		driver.get(PropertyReader.getProperty("url"));
		patientClick.click();
		userField.sendKeys(userName);
		passwordField.sendKeys(pass);
		login.click();
		String loginUserName=this.getLoginUserName();
		//Assert.assertTrue(loginUserName.contains(userName));
		}
	
	public void selectGender(String gen) {
		if(gen.equals("male"))
			male.click();
		else 
			female.click();
	}
	
	public void createAccount() {
		//patientClick.click();
		clickCreateAcc.click();
		name.sendKeys(fileReader.readExcel("Patient_newAccount",1,0));
		address.sendKeys(fileReader.readExcel("Patient_newAccount",1,1));
		city.sendKeys(fileReader.readExcel("Patient_newAccount",1,2));
		this.selectGender(fileReader.readExcel("Patient_newAccount",1,3));
		email.sendKeys(fileReader.readExcel("Patient_newAccount",1,4));
		patient_pass.sendKeys(fileReader.readExcel("Patient_newAccount",1,5));
		String check_email=email_available.getText();
		Assert.assertFalse(check_email.equals("Email already exists ."));
		patient_confirmPass.sendKeys(fileReader.readExcel("Patient_newAccount",1,6));
		doc_submitBtn.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
		
	public String getLoginUserName() {

			return loggedInAs.getText();
		}

	public void invalidLoginAdmin() {
		
			patientClick.click();
			userField.sendKeys(PropertyReader.getProperty("invalidUsername"));
			passwordField.sendKeys(PropertyReader.getProperty("invalidPassword"));
			login.click();   
			Assert.assertTrue(invalidMsg.getText().equals("Invalid username or password"));
	
	}
	
	
}
