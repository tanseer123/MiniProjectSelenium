package com.hms.fileHandle;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {
	private String filePath=PropertyReader.getProperty("filePath");
	private String fileName=PropertyReader.getProperty("fileName");
   
	public String readExcel(String sheetName, int row, int cell) {
		XSSFWorkbook workBook;
    	String data="";
    	
    	try {
    		File file = new File(filePath+""+fileName);
		    FileInputStream inputStream = new FileInputStream(file);
		    workBook = new XSSFWorkbook(inputStream);
		    Sheet sheet = workBook.getSheet(sheetName);
		    Cell cel=sheet.getRow(row).getCell(cell);
		    data=new DataFormatter().formatCellValue(cel);//format the data into string
		    workBook.close();	    
    }
    catch(Exception e) {
    	System.out.println(e.getMessage());
    	
    }
		return data;
	}  
}