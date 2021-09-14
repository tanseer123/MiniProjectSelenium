package com.hms.Mini_Project;

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

public class Doctors {
	
	private WebDriver driver;
	
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
	

	
	public Doctors(WebDriver driver){
		this.driver=driver; 
		PageFactory.initElements(driver, this);
	}
	
	public void add_doctorSpecification() {
		doctors_click.click();
		docSpecif.click();	
		specName.sendKeys("Cardiology");
		submit_btn.click();
		String added =confirm_add.getText(); 
		Assert.assertEquals(added, "Doctor Specialization added successfully !!");
		
	}
	
	public void add_doctor() {
		  doctors_click.click();
		  click_addDoctor.click();
		  Select drpSpecial = new Select(drpdown);
		  drpSpecial.selectByVisibleText("Cardiology");
		  doc_name.sendKeys("Santhosh");
		  doc_address.sendKeys("Karnataka India");
		  doc_fees.sendKeys("100");
		  doc_contact.sendKeys("985674231");
		  doc_email.sendKeys("santosh@gmail.com");
		  doc_pass.sendKeys("Santosh123");
		  doc_confirmPass.sendKeys("Santosh123");
		  doc_submitBtn.click();
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
	  }
	
	public void delete_doctor() {
		 doctors_click.click();
		 manageDoctor.click();
		 List<WebElement>  rows = total_doc;
		 int count =0;
		 for(WebElement row:rows) {
			  count++;
			  String name=row.getText();
			  if(name.equals("Santhosh")) {
				 driver.findElement(By.xpath("//*[@id=\"sample-table-1\"]/tbody/tr["+count+"]/td[5]/div[1]/a[2]/i")).click();
				 Alert alert = driver.switchTo().alert();   
			     alert.accept();
			 }
			  
		  }
		  
		  
	  }
	
	

}
