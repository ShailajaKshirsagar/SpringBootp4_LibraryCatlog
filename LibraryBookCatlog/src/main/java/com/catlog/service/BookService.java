package com.catlog.service;

import com.catlog.entity.Book;

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
}
