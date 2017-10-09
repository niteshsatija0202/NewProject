package com.provar.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcel {

	@Test
	public static  String[][] read() throws Throwable{


		FileInputStream file=new FileInputStream(ReadPropertyFile.getProperty().get("ReadExcelFile"));

		XSSFWorkbook wb=new XSSFWorkbook(file);
		XSSFSheet sheet=wb.getSheet(ReadPropertyFile.getProperty().get("ReadExcelFile_Sheet"));
		int rowcount=sheet.getLastRowNum();
		int columcount=sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rowcount][columcount];

		for(int i=0; i<rowcount;i++){
			XSSFRow row=sheet.getRow(i);
			for(int j=0; j<columcount;j++){
				XSSFCell cell=row.getCell(j);
				String input=cellToString(cell);
				data[i][j]=input;

			}
		}
		return data;
	}




	public static String cellToString(XSSFCell cell){

		Object result=null;
		
		int type=cell.getCellType();
		

		switch(type){
		case Cell.CELL_TYPE_STRING:
			result=cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			result=cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result=cell.getBooleanCellValue();
			break;
		}
		return result.toString(); 

	}
	@Test
	public void test() throws Throwable{
		String[][] data=read();
		for(int a=0; a<data.length; a++) {
			for(int b=0; b<data[b].length; b++) {
				System.out.println(data[a][b]+"\t");
			}
			System.out.println();
		}

	}

}

