package com.catlog.helper;

import com.catlog.entity.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

//Generate excel sheet
public class GenerateExcelFromDB {

    //This method create Excel sheet
    public static ByteArrayInputStream bookToExcel(List<Book> bookList) throws IOException {

        //create Header
        String[] columns = {"ID", "Name", "Author Name", "ISBN", "Available Copies"};

        try {
            //create workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            //create sheet
            Sheet sheet = workbook.createSheet("BookData");

            //create row
            Row row = sheet.createRow(0);

            //create cell
            for (int i = 0; i < columns.length; i++) {

                Cell cell = row.createCell(i);
                cell.setCellValue(columns[i]);
            }

            //Add data in rows
            int rowIdx = 1;
            for (Book book : bookList) {

                Row row1 = sheet.createRow(rowIdx++);

                row1.createCell(0).setCellValue(book.getId());
                row1.createCell(1).setCellValue(book.getName());
                row1.createCell(2).setCellValue(book.getAuthor());
                row1.createCell(3).setCellValue(book.getIsbn());
                row1.createCell(4).setCellValue(book.getAvailableCopies());
            }
            //write data in outputstream
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            throw e; // rethrow if needed
        }
    }
}
