package com.catlog.helper;

import com.catlog.entity.Book;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataReadHelper
{
    //check excel file format
    public static boolean checkExcelFileFormat(MultipartFile file){

       String contentType = file.getContentType();
       if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
           return true;
       }
       else {
           return false;
       }
    }
    //convertExcel data into list of books
    public static List<Book> convertExcelDataToListBook(InputStream is){

        List<Book> list = new ArrayList<>();

        try {

            XSSFWorkbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);

            int rowNum = 0;
            Iterator<Row> rows  = sheet.iterator();

            while (rows.hasNext()){

                Row currenTrow = rows.next();

                //skips header
                if(rowNum == 0){
                    rowNum++;
                    continue;
                }

                Book book = new Book();

                book.setName(currenTrow.getCell(0).getStringCellValue());
                book.setAuthor(currenTrow.getCell(1).getStringCellValue());
                book.setIsbn(currenTrow.getCell(2).getStringCellValue());
                book.setAvailableCopies((int) currenTrow.getCell(3).getNumericCellValue());

                list.add(book);
            }
            workbook.close();
        }
        catch (Exception e){
            throw new RuntimeException("Failed to parse excel file " +e.getMessage());
        }
        return list;
    }

}
