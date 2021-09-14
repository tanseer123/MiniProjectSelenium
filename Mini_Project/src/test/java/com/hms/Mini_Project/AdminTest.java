package com.hms.Mini_Project;
import org.testng.annotations.Test;
import org.testng.Assert;


import com.hms.Mini_Project.Admin;
import com.hms.Mini_Project.Doctors;
import com.fileHandle.PropertyReader;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;

public class AdminTest{
	
	String baseUrl=null;
	String expectedTitle= null;
	String browserType=null;
	WebDriver driver=null;
	String currentUrl;
	Admin admin_object;
	Doctors doctor_obj;
  @Test
  public void f() throws InterruptedException {
	  admin_object=new Admin(driver);
	  admin_object.loginAdmin();
	  String loginUserName=admin_object.getLoginUserName();
	  Assert.assertTrue(loginUserName.contains("Admin"));
	  doctor_obj=new Doctors(driver);
	  doctor_obj.add_doctorSpecification();
	  //Assert.assertTrue(loginUserName.contains("ADMIN | ADD DOCTOR SPECIALIZATION"));
	  doctor_obj.add_doctor();
	  //AssertJUnit.assertTrue(loginUserName.contains("ADMIN | ADD DOCTOR"));
	  doctor_obj.delete_doctor();
	 // AssertJUnit.assertTrue(loginUserName.contains("ADMIN | MANAGE DOCTORS"));
  }
  
  
  
  
  @BeforeMethod
  public void adminTest() {
	  	
	  	String browserType=PropertyReader.getProperty("browser");
		String DriverPath=PropertyReader.getProperty("chromeDriverPath");
		System.setProperty("webdriver.chrome.driver", DriverPath);
		driver=new ChromeDriver();
		driver.get(PropertyReader.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
