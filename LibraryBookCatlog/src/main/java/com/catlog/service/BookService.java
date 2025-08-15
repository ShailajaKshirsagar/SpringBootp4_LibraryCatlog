package com.catlog.service;

import com.catlog.entity.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface BookService
{
    //save book
    String saveBook(Book book);

    //get all data
    List<Book> getAllBook();

    //getby id method added
    Book getBookbyid(int id);

    //deleteBook
    String deleteBook(int id);

    //update Book
    Book updateBook(int id, Book newBook);

    //save excel data in database
    String saveExcelData(MultipartFile file);

    //Export data in excel with password protection
    ByteArrayInputStream exportDataInExcel(String password) throws IOException;
}
