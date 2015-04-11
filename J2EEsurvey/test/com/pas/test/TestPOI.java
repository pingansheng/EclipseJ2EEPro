package com.pas.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class TestPOI {

	@Test
	public void TestFirst() {
		HSSFWorkbook excel = new HSSFWorkbook();
		try {
			HSSFSheet sheet=excel.createSheet("¹¤×÷±í");
			HSSFRow row=sheet.createRow(0);
			HSSFCell cell=row.createCell(0);
			cell.setCellValue("test");
			row.createCell(1).setCellValue(false);
			HSSFCellStyle cellStyle=excel.createCellStyle();
			HSSFDataFormat dataFormat=excel.createDataFormat();
			cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd hh:mm:ss"));
			row.createCell(2).setCellValue(new Date());
			row.getCell(2).setCellStyle(cellStyle);
			
			row.createCell(3).setCellValue(Calendar.getInstance());
			row.getCell(3).setCellStyle(cellStyle);
			
			
			row.createCell(4).setCellValue(123456789.987654321);
			cellStyle=excel.createCellStyle();
			cellStyle.setDataFormat(dataFormat.getFormat("###,#.000"));
			row.getCell(4).setCellStyle(cellStyle);
			
			row.createCell(5).setCellValue(new HSSFRichTextString("dddddddddddddddddddddddddddddddddd"));
			cellStyle=excel.createCellStyle();
			cellStyle.setWrapText(true);
			row.getCell(5).setCellStyle(cellStyle);
			
			sheet.setColumnWidth(2, 20*256);
			sheet.autoSizeColumn(3);;
			//ok
			excel.write(new FileOutputStream(new File("excel.xls")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
