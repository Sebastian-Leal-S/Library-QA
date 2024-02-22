package com.periferia.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GestorArchivosExcel {

    private static XSSFSheet excelWSheet;
    private static XSSFWorkbook excelWBook;
    private static XSSFCell cell;


    public static String getCellData(int rowNum, int colNum) {
        cell = excelWSheet.getRow(rowNum).getCell(colNum);
        String CellData = "";
        if (cell.getCellType() == null) {
            return "";
        } else {
            try {
                CellData = cell.getStringCellValue();
            } catch (Exception e) {
                CellData = Double.toString(cell.getNumericCellValue()).split("\\. ")[0];
            }
        }

        return CellData;
    }

    public static Object[][] getTableArray(String filePath, String sheetName) {
        String[][] tabArray = null;

        try {
            FileInputStream ExcelFile = new FileInputStream(filePath);
            //ACCESS THE REQUIRED TEST DATA SHEET
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
            int startRow = 1;
            int startCol = 0;
            int ci, cj;
            int totalRows = excelWSheet.getLastRowNum();
            int totalCols = excelWSheet.getRow(0).getPhysicalNumberOfCells();
            tabArray = new String[totalRows][totalCols];
            ci = 0;
            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j <= totalCols - 1; j++, cj++) {
                    tabArray[ci][cj] = getCellData(i, j);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read the excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the excel sheet");
            e.printStackTrace();
        }
        return (tabArray);
    }
}
