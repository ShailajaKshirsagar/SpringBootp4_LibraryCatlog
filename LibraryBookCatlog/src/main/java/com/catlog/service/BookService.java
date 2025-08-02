package com.catlog.service;

import com.catlog.entity.Book;

import java.util.List;

public interface BookService
{
    String saveBook(Book book);

    List<Book> getAllBook();

    Book getBookbyid(int id);
}
