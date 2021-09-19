package com.hms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.hms.fileHandle.ExcelFileReader;

public class DoctorDashboard {
	
	private WebDriver driver;
	private ExcelFileReader fileReader;

	@FindBy(how=How.XPATH,using="//*[@id='container']/div/div/div[1]/div/div/p/a")
	WebElement profileclick;
	
	@FindBy(how=How.CSS,using="select[name='Doctorspecialization']")
	WebElement drpdown;
	
	@FindBy(how=How.NAME,using="docname")
	WebElement docname;
    
	@FindBy(how=How.NAME,using="clinicaddress")
	WebElement clinicAddress;
    
	@FindBy(how=How.NAME,using="docfees")
	WebElement  doctorFees;
    
    @FindBy(how=How.NAME,using="doccontact")
   	WebElement doctorContact;
    
    @FindBy(how=How.NAME,using="submit")
    WebElement submit;
    
    @FindBy(how=How.XPATH,using="a[href=''appointment-history.php]")
    WebElement appointment;
    
    @FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[3]/a/div/div[2]")
    WebElement patient;
    
    @FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[3]/ul/li[1]/a")
    WebElement addpat;
    
    @FindBy(how=How.NAME,using="patname")
   	WebElement patientname;
    
    @FindBy(how=How.CSS,using="input[name='patcontact']")
   	WebElement patientcontact;
    
    @FindBy(how=How.ID,using="patemail")
   	WebElement patientemail;
    
	@FindBy(how=How.CSS,using="label[for='rg-male']")
	WebElement female;
	
	@FindBy(how=How.CSS,using="label[for='rg-male']")
	WebElement male;
    
    @FindBy(how=How.NAME,using="pataddress")
   	WebElement patientaddress;
    
    @FindBy(how=How.NAME,using="patage")
    WebElement patientage;
    
    @FindBy(how=How.NAME,using="medhis")
    WebElement medicalhistory;
    
    @FindBy(how=How.NAME,using="submit")
    WebElement add;
    
    @FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[4]/a")
    WebElement patient_search;
    
    @FindBy(how=How.NAME,using="searchdata")
    WebElement name_search;
    
    @FindBy(how=How.ID,using="submit")
    WebElement Search;
    
    @FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[3]/ul/li[2]/a/span")
    WebElement manage_patient;

    
    @FindBy(how=How.XPATH,using="//*[@id='sample-table-1']/tbody/tr[1]/td[7]/a[2]")
    WebElement viewpatient;
    
    @FindBy(how=How.XPATH,using="//*[@id='container']/div/div/div/p/button")
    WebElement add_Patient_history;
    
    @FindBy(how=How.NAME,using="bp")
    WebElement Patient_BloodPressure;
    
    @FindBy(how=How.NAME,using="bs")
    WebElement Patient_Bloodsugar;
    
    @FindBy(how=How.NAME,using="weight")
    WebElement Patient_Weight;
    
    @FindBy(how=How.NAME,using="temp")
    WebElement Patient_Temperature;
    
    @FindBy(how=How.NAME,using="pres")
    WebElement Patient_prescription;
    
    @FindBy(how=How.CSS,using="button[name='submit']")
    WebElement Patient_submit;
    
    
    public DoctorDashboard(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this); 
        fileReader=new ExcelFileReader();
    }
    
    public void updateprofile() {
		profileclick.click();
		docname.clear();
		clinicAddress.clear();
		doctorFees.clear();
		doctorContact.clear();
		Select dropdown = new Select(drpdown);
		dropdown.selectByVisibleText(fileReader.readExcel("Doctor_updateDoctor",1,0));
		docname.sendKeys(fileReader.readExcel("Doctor_updateDoctor",1,1));
		clinicAddress.sendKeys(fileReader.readExcel("Doctor_updateDoctor",1,2));
		doctorFees.sendKeys(fileReader.readExcel("Doctor_updateDoctor",1,3));
		doctorContact.sendKeys(fileReader.readExcel("Doctor_updateDoctor",1,4));
		submit.click();
		driver.switchTo().alert().accept();
	}
	
    public void selectGender(String gen) {
		if(gen.equals("male"))
			male.click();
		else 
			female.click();
	}

	public void add_patient() {
	    	patient.click();
	    	addpat.click();
	    	patientname.sendKeys(fileReader.readExcel("Doctor_addPatient",1,0));
	    	patientcontact.sendKeys(fileReader.readExcel("Doctor_addPatient",1,1));
	    	patientemail.sendKeys(fileReader.readExcel("Doctor_addPatient",1,2));
	    	this.selectGender(fileReader.readExcel("Doctor_addPatient",1,3));
	    	patientaddress.sendKeys(fileReader.readExcel("Doctor_addPatient",1,4));
	    	patientage.sendKeys(fileReader.readExcel("Doctor_addPatient",1,5));
	    	medicalhistory.sendKeys(fileReader.readExcel("Doctor_addPatient",1,6));
    	    add.click();
	   }
    
    public void appointmentHistory() {
		appointment.click();
	}
    

	public void search_patient() {
		patient_search.click();
		
		name_search.sendKeys(fileReader.readExcel("Doctor_searchPatient",1,0));
		Search.click();
	}
	
	   

    public void manage_patient() {
    	 patient.click();
		 manage_patient.click();
		 viewpatient.click();
		 add_Patient_history.click();
		 Patient_BloodPressure.sendKeys(fileReader.readExcel("Doctor_patientDetails",1,0));
		 Patient_Bloodsugar.sendKeys(fileReader.readExcel("Doctor_patientDetails",1,1));
		 Patient_Weight.sendKeys(fileReader.readExcel("Doctor_patientDetails",1,2));
		 Patient_Temperature.sendKeys(fileReader.readExcel("Doctor_patientDetails",1,3));
		 Patient_prescription.sendKeys(fileReader.readExcel("Doctor_patientDetails",1,4));
		 Patient_submit.click();
		 driver.switchTo().alert().accept();
		 driver.switchTo().defaultContent();
		 
	}

}
