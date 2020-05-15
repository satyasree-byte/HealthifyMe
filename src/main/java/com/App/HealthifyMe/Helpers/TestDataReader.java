package com.App.HealthifyMe.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataReader {
	
	public static final String ExcelPath = "C:\\Users\\satyasree\\HealthifyMe_Master\\src\\test\\resources\\TestData.xlsx";
	
	private static FileInputStream Stream;
	private static XSSFWorkbook Wb;
	private static XSSFSheet Sheet;
	private static XSSFRow row;
	
	
	public static void LoadExcel() throws Exception {
		
		File f = new File(ExcelPath);
		Stream = new FileInputStream(f);
		Wb = new XSSFWorkbook(Stream);
		Sheet = Wb.getSheet("Data");
		Stream.close();
	}

	public static Map<String, Map<String,String>> getTestData() throws Exception{
		if(Sheet == null)
			LoadExcel();
		
		Map<String, Map<String,String>> SuperMap = new HashMap<String, Map<String,String>>();
		Map<String,String> ExcelMap = new HashMap<String, String>();
		
		DataFormatter formatter = new DataFormatter();
		for(int i = 1 ; i< Sheet.getLastRowNum()+1; i++) {
			row = Sheet.getRow(i);
			String Key = formatter.formatCellValue(row.getCell(0));
			
			int ColumnNum = row.getLastCellNum();
			for(int j = 1; j< ColumnNum;j++) {
				String Value =  formatter.formatCellValue(row.getCell(j));
				ExcelMap.put(Key, Value);
				
			}
			SuperMap.put("TOTALDATA", ExcelMap);	
		}
		
		return SuperMap;	
	}
	
	public static String getValue(String Key) throws Exception {
		
		Map<String, String> myDataMap = getTestData().get("TOTALDATA");
		String myValue = myDataMap.get(Key);
		return myValue;
		
	}
	
}
