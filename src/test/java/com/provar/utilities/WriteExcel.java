package com.provar.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class WriteExcel {
	@Test
	public static void Write(ArrayList list, String path,String sheetName) throws Throwable {

		//File file1=new File(ReadPropertyFile.getProperty().get("ReadExcelFile"));
		File file1=new File(path);
		FileInputStream input=new FileInputStream(file1);

		XSSFWorkbook wb=new XSSFWorkbook(input);
		//XSSFSheet sheet=wb.getSheet(ReadPropertyFile.getProperty().get("ReadExcelFile_Sheet"));
		XSSFSheet sheet=wb.getSheet(sheetName);



		for(int i=0;i<list.size();i++){

			//sheet.getRow(i).getCell(0).setCellValue(list.get(i).toString());
			sheet.createRow(i).createCell(0).setCellValue(list.get(i).toString());

		}
		FileOutputStream output=new FileOutputStream(file1);
		wb.write(output);
		output.close(); 
	}

@Test
	public static void test() throws Throwable {
		ArrayList list1=new ArrayList();
		list1.add("pass");
		list1.add("pass");
		list1.add("3");
		list1.add("new");
		list1.add("pass");
		list1.add("67");
		list1.add("p7");

		//WriteExcel.Write(list1);
		WriteExcel.Write(list1,ReadPropertyFile.getProperty().get("ReadExcelFile"),ReadPropertyFile.getProperty().get("ReadExcelFile_Sheet"));


	}

}
