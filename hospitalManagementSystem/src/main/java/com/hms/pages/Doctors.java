package com.hms.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.hms.fileHandle.ExcelFileReader;
import com.hms.fileHandle.PropertyReader;

public class Doctors {
	
	private WebDriver driver;
	
	private ExcelFileReader fileReader;
	
	@FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[2]/a/div/div[2]")
	WebElement doctors_click;
	
	@FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[2]/ul/li[1]/a")
	WebElement docSpecif;
	
	@FindBy(how=How.CSS,using="input[name='doctorspecilization']")
	WebElement specName;
	
	@FindBy(how=How.CSS,using="button[name='submit']")
	WebElement submit_btn ;
	
	@FindBy(how=How.XPATH,using="//*[@id='container']/div/div[1]/div[1]/div/div/div/div[2]/p")
	WebElement confirm_add;
	
	@FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[2]/ul/li[2]/a")
	WebElement click_addDoctor;
	
	@FindBy(how=How.CSS,using="select[name='Doctorspecialization']")
	WebElement drpdown;
	
	@FindBy(how=How.NAME,using="docname")
	WebElement doc_name;
	
	@FindBy(how=How.NAME,using="clinicaddress")
	WebElement doc_address;
	
	@FindBy(how=How.NAME,using="docfees")
	WebElement doc_fees;
	
	@FindBy(how=How.NAME,using="doccontact")
	WebElement doc_contact;
	
	@FindBy(how=How.NAME,using="docemail")
	WebElement doc_email;
	
	@FindBy(how=How.NAME,using="npass")
	WebElement doc_pass;
	
	@FindBy(how=How.NAME,using="cfpass")
	WebElement doc_confirmPass;
	
	@FindBy(how=How.ID,using="submit")
	WebElement doc_submitBtn;
	
	@FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[2]/ul/li[3]/a")
	WebElement manageDoctor;
	
	@FindBy(how=How.XPATH,using="//*[@id='sample-table-1']/tbody/tr/td[3]")
	List<WebElement> total_doc;
	
	@FindBy(how=How.CSS,using=".mainTitle")
	WebElement mainTitle;
	
	

	public Doctors(WebDriver driver){
		this.driver=driver; 
		PageFactory.initElements(driver, this);
	}
	
	public void add_doctorSpecialization() {
		doctors_click.click();
		docSpecif.click();	
		String mtitle =this.main_title(); 
		Assert.assertTrue(mtitle.equals("ADMIN | ADD DOCTOR SPECIALIZATION"));
		fileReader=new ExcelFileReader();
		String specialName= fileReader.readExcel("Admin_doctorSpecialization",1,0);
		specName.sendKeys(specialName);
		submit_btn.click();
		String added =confirm_add.getText(); 
		Assert.assertEquals(added, "Doctor Specialization added successfully !!");
		
	}
	
	public void add_doctor() {
		  doctors_click.click();
		  click_addDoctor.click();
		  String mtitle =this.main_title(); 
		  fileReader=new ExcelFileReader();
		  Assert.assertTrue(mtitle.equals("ADMIN | ADD DOCTOR"));
		  Select drpSpecial = new Select(drpdown);
		  drpSpecial.selectByVisibleText(fileReader.readExcel("Admin_doctorDetails",1,0));
		  doc_name.sendKeys(fileReader.readExcel("Admin_doctorDetails",1,1));
		  doc_address.sendKeys(fileReader.readExcel("Admin_doctorDetails",1,2));
		  doc_fees.sendKeys(fileReader.readExcel("Admin_doctorDetails",1,3));
		  doc_contact.sendKeys(fileReader.readExcel("Admin_doctorDetails",1,4));
		  doc_email.sendKeys(fileReader.readExcel("Admin_doctorDetails",1,5));
		  doc_pass.sendKeys(fileReader.readExcel("Admin_doctorDetails",1,6));
		  doc_confirmPass.sendKeys(fileReader.readExcel("Admin_doctorDetails",1,7));
		  doc_submitBtn.click();
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
	  }
	
	public void delete_doctor() {
		 doctors_click.click();
		 manageDoctor.click();
		 fileReader=new ExcelFileReader();
		 String mtitle =this.main_title(); 
			Assert.assertTrue(mtitle.equals("ADMIN | MANAGE DOCTORS"));
		 List<WebElement>  rows = total_doc;
		 int count =0;
		 for(WebElement row:rows) {
			  count++;
			  String name=row.getText();
			  if(name.equals(fileReader.readExcel("Admin_doctorName",1,0))) {
				 driver.findElement(By.xpath("//*[@id=\"sample-table-1\"]/tbody/tr["+count+"]/td[5]/div[1]/a[2]/i")).click();
				 Alert alert = driver.switchTo().alert();   
			     alert.accept();
			     break;
			 }
		  }		  		  
	  }
	
	public String main_title() {
		return mainTitle.getText();
	}
	
	

}
