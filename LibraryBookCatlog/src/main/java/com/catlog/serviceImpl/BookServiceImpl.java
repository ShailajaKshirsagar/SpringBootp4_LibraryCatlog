package com.catlog.serviceImpl;

import com.catlog.entity.Book;
import com.catlog.repository.BookRepository;
import com.catlog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService
{
    //inject repository in this
    @Autowired
    private BookRepository repository;

    @Override
    public String saveBook(Book book) {
        repository.save(book);
        return "Book added";
    }

    @Override
    public List<Book> getAllBook() {
        List<Book> bookList =repository.findAll();
        return bookList;
    }

    @Override
    public Book getBookbyid(int id) {
    Book bookbyid = repository.findById(id).orElseThrow(()-> new NullPointerException("Book not found"+id));
    return bookbyid;
    }
}
