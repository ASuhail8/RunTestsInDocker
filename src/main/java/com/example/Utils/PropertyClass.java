package com.example.Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class PropertyClass {

    public static String getProperty(String key) {
        //String path = System.getProperty("path");
        Properties prop = new Properties();
        InputStream inputStream = PropertyClass.class.getClassLoader().getResourceAsStream("config/some.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(key);

    }


    public static ArrayList<String> getExcelData(String sheetName, String testName) throws IOException {

        FileInputStream fis = new FileInputStream("src/test/resources/testdata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int count = workbook.getNumberOfSheets();
        ArrayList<String> al = new ArrayList<String>();
        XSSFSheet sheet;
        for (int i = 0; i < count; i++) {
            if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetName)) {
                sheet = workbook.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.rowIterator();
                while (rowIterator.hasNext()) {
                    Row rows = rowIterator.next();
                    Iterator<Cell> cellIterator = rows.cellIterator();
                    if (cellIterator.next().getStringCellValue().equalsIgnoreCase(testName)) {
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            if (cell.getCellType() == CellType.STRING) {
                                al.add(cell.getStringCellValue());
                            } else {
                                al.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                            }
                        }
                    }
                }

            }
        }
        workbook.close();
        return al;

    }


    public static List<List<String>> data() throws IOException {

        List<List<String>> list = new ArrayList<List<String>>();
        ArrayList<String> testcaseList = getTestcaseNames("Sheet1");
        for (int i = 0; i < testcaseList.size(); i++) {
            ArrayList<String> dataList = getExcelData("Sheet1", testcaseList.get(i));
            list.add(dataList);
        }
        return list;
    }

    public static ArrayList<String> getTestcaseNames(String sheetname) throws IOException {
        InputStream inputStream = PropertyClass.class.getClassLoader().getResourceAsStream("testdata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        int count = workbook.getNumberOfSheets();
        ArrayList<String> al = new ArrayList<String>();
        XSSFSheet sheet;
        for (int i = 0; i < count; i++) {
            if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetname)) {
                sheet = workbook.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.rowIterator();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Row rows = rowIterator.next();
                    Iterator<Cell> cellIterator = rows.cellIterator();
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING) {
                        al.add(cell.getStringCellValue());
                    } else {
                        al.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                    }
                }
            }
        }
        workbook.close();
        return al;
    }


}
