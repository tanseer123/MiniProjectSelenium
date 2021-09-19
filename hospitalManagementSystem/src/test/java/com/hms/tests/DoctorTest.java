package com.hms.tests;

import org.testng.annotations.Test;

import com.hms.fileHandle.PropertyReader;
import com.hms.pages.Admin;
import com.hms.pages.DoctorDashboard;
import com.hms.pages.DoctorLogin;

import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;



	public class DoctorTest {
		
		WebDriver driver=null;
		DoctorLogin doctor_object;
		DoctorDashboard db_obj;
		
		
		 @Test(priority=0)
		  public void TC_HMS_11()  {
			 doctor_object=new DoctorLogin(driver);
			 doctor_object.invalidLoginAdmin();
		  }
	
		@Test(priority=1)
		public void TC_HMS_10()  {
		doctor_object=new DoctorLogin(driver);
		doctor_object.loginDoctor();
		}	

		@Test(priority=2,dependsOnMethods= {"TC_HMS_10"})
		public void TC_HMS_13() {
			db_obj.updateprofile();
		}

		@Test(priority=3,dependsOnMethods= {"TC_HMS_10"})
		public void TC_HMS_14() {
			db_obj.add_patient();
		}
		
		@Test(priority=4,dependsOnMethods= {"TC_HMS_10"})
		public void TC_HMS_15()  {
			db_obj.manage_patient();
		}
		
		@Test(priority=5,dependsOnMethods= {"TC_HMS_10"})
		public void TC_HMS_16() {
			db_obj.search_patient();
		}
		
		
		@BeforeMethod
		public void beforeMethod() {
			db_obj=new DoctorDashboard(driver);
		}
		
		@BeforeClass
		public void beforeClass() {
		String browserType=PropertyReader.getProperty("browser");
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