package com.hms.tests;

import com.hms.fileHandle.PropertyReader;
import com.hms.pages.DoctorLogin;
import com.hms.pages.Doctors;
import com.hms.pages.PatientLogin;
import com.hms.pages.Patients;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;



	public class PatientTest {
		
		WebDriver driver=null;
		PatientLogin patient_object;
		Patients patient_obj;
		
		 @Test(priority=0)
		  public void TC_HMS_19()  {
			 patient_object=new PatientLogin(driver);
			 patient_object.invalidLoginAdmin();
		  }
		
		@Test(priority=1)
		public void TC_HMS_17() {
			patient_object=new PatientLogin(driver);
			patient_object.createAccount();
			
		}
		
		@Test(priority=2)
		public void TC_HMS_18() {
		patient_object=new PatientLogin(driver);
		patient_object.patientLogin();
		}
		
		@Test(priority=3,dependsOnMethods= {"TC_HMS_18"})
		public void TC_HMS_22() {
			patient_obj.bookAppointment();
			
		}
		
		@Test(priority=4,dependsOnMethods= {"TC_HMS_18"})
		public void TC_HMS_23() {
			patient_obj.cancelAppointment();
		}
		
		@Test(priority=5,dependsOnMethods= {"TC_HMS_18"})
		public void TC_HMS_24() {
			patient_obj.appointmentHistory();
		}
		
		@BeforeMethod
		public void beforeMethod() {
			patient_obj=new Patients(driver);
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