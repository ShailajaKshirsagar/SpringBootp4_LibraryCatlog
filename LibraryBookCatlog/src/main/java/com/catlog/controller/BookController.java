package com.catlog.controller;

import com.catlog.entity.Book;
import com.catlog.service.BookService;
import org.aspectj.weaver.ast.Literal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookCatlog")
public class BookController
{
    //inject service in this
    @Autowired
    private BookService bookService;

    //add data
    @PostMapping("/addBook")
    public ResponseEntity<String> addBookData(@RequestBody Book book){
        System.err.println(book);
        bookService.saveBook(book);
        return  new ResponseEntity<>("Book added", HttpStatus.CREATED);
    }

    //get all data
    @GetMapping("/getAllBookData")
    public ResponseEntity<List<Book>> getAllData(){
        List<Book> bookList = bookService.getAllBook();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    //get Data byt id
    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        Book book = bookService.getBookbyid(id);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
}
