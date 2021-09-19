package com.hms.tests;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.hms.fileHandle.PropertyReader;
import com.hms.pages.Admin;
import com.hms.pages.Doctors;
import com.hms.pages.SearchPatient;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AdminTest{
	
		String baseUrl=null;
		String expectedTitle= null;
		String browserType=null;
		WebDriver driver=null;
		String currentUrl;
		Admin admin_object;
		Doctors doctor_obj;
		
		
	 @Test(priority=0)
	  public void TC_HMS_03()  {
		 admin_object=new Admin(driver);
		  admin_object.invalidLoginAdmin();
	  }
	 
     @Test(priority=1)
	  public void TC_HMS_02()  {
    	 admin_object=new Admin(driver);
		  admin_object.loginAdmin();
	  }
	  
	 @Test(priority=2,dependsOnMethods= {"TC_HMS_02"})
	 public void TC_HMS_05() throws IOException {
		  doctor_obj.add_doctorSpecialization();
	 }
	 
	 @Test(priority=3,dependsOnMethods= {"TC_HMS_02"})
	 public void TC_HMS_06(){
		 doctor_obj.add_doctor();
	 }
	 
	 @Test(priority=4,dependsOnMethods= {"TC_HMS_02"})
	 void TC_HMS_07(){
		 doctor_obj.delete_doctor();
	 }
	 
	 @Test(priority=5,dependsOnMethods= {"TC_HMS_02"})
	 void TC_HMS_09() {
		 SearchPatient srch=new SearchPatient(driver);
		  srch.searchName();
	 }
	 
	 @BeforeMethod
	 public void beforeMethod() {
		 doctor_obj=new Doctors(driver);
	 }
	 
	@BeforeClass
	public void beforeClass() { 	
			String DriverPath=PropertyReader.getProperty("chromeDriverPath");
			System.setProperty("webdriver.chrome.driver", DriverPath);
			driver=new ChromeDriver();
			driver.get(PropertyReader.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  
	}
	
	@AfterClass
	public void afterMethod() {
		  driver.quit();
	}

}
