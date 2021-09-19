package com.hms.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.hms.fileHandle.ExcelFileReader;

public class Patients {
	
	WebDriver driver;
	private ExcelFileReader fileReader;

	@FindBy(how=How.CSS,using="a[href='book-appointment.php']")
	WebElement clickBookAppointment;
	
	@FindBy(how=How.CSS,using="select[name='Doctorspecialization']")
	WebElement drpDownSpecial;

	@FindBy(how=How.CSS,using="select[id='doctor']")
	WebElement drpDownDoctors;
	
	@FindBy(how=How.NAME,using="appdate")
	WebElement date;
	
	@FindBy(how=How.ID,using="timepicker1")
	WebElement time;
	
	@FindBy(how=How.CSS,using="button[type='submit']")
	WebElement bookAppiont_Btn;
	
	@FindBy(how=How.XPATH,using="//*[@id='sidebar']/div/nav/ul/li[3]/a/div/div[2]/span")
	WebElement appointHistory;
	
	@FindBy(how=How.XPATH,using="//*[@id='sample-table-1']/tbody/tr/td[2]")
	List<WebElement> total_appoint;
	
	
	public Patients(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		fileReader =new ExcelFileReader();
	}
	
	public void bookAppointment() {
		clickBookAppointment.click();
		Select drpSpecial = new Select(drpDownSpecial);
		drpSpecial.selectByVisibleText(fileReader.readExcel("Patient_bookAppointment",1,0));
		Select drpDoctor = new Select(drpDownDoctors);
		drpDoctor.selectByVisibleText(fileReader.readExcel("Patient_bookAppointment",1,1));
		date.sendKeys(fileReader.readExcel("Patient_bookAppointment",1,2));
		time.sendKeys(fileReader.readExcel("Patient_bookAppointment",1,3));
		bookAppiont_Btn.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
	}
	
	public void cancelAppointment() {
		 appointHistory.click();
		 List<WebElement>  rows = total_appoint;
		 int count=0;
		 for(WebElement row:rows) {
			 count++;
			  String name=row.getText();
			  if(name.equals(fileReader.readExcel("Patient_cancelAppointment",1,0))) {
				 String status=driver.findElement(By.xpath("//*[@id='sample-table-1']/tbody/tr["+count+"]/td[7]")).getText();
				 if(status.equals("Active")) {
				 driver.findElement(By.xpath("//*[@id='sample-table-1']/tbody/tr["+count+"]/td[8]/div[1]/a")).click();
				 Alert alert = driver.switchTo().alert();   
			     alert.accept();
			     count--;
			     break;
				 }
				 else
					 continue;
			 }
		  }
	}
	
	public void appointmentHistory() {
		appointHistory.click();
	}
	
	
}
