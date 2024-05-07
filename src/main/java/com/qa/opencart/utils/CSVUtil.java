package com.qa.opencart.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVUtil {
	
	public static Object data[][];
	public static Object[][] getTestDatafromCSV() {
		String csvFile=".\\src\\test\\resources\\testdata\\regTestDataCs.csv";
		try {
			CSVReader reader=new CSVReader(new FileReader(csvFile));
			List<String[]> rows= reader.readAll();
			reader.close();
			
			data=new Object[rows.size()][];
			for(int i=0;i<rows.size();i++) {
				data[i]=rows.get(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		return data;
		
	}
	@Test(dataProvider = "getTestDatafromCSV")
	public void getcsvdata(String firstName,String lastName,String telephone,String password,String subscribe) {
		System.out.println(firstName+","+lastName+","+telephone+","+password+","+subscribe);
	}
}
