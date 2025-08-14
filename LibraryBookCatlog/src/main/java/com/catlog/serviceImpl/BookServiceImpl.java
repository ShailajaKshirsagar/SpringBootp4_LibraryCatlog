package com.catlog.serviceImpl;

import com.catlog.entity.Book;
import com.catlog.helper.ExcelDataReadHelper;
import com.catlog.repository.BookRepository;
import com.catlog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BookServiceImpl implements BookService
{
    //inject repository in this
    @Autowired
    private BookRepository repository;

    //save book implemented
    @Override
    public String saveBook(Book book) {
        repository.save(book);
        return "Book added";
    }

    //getAll book implemented
    @Override
    public List<Book> getAllBook() {
        List<Book> bookList =repository.findAll();
        return bookList;
    }

    //getByid implemented
    @Override
    public Book getBookbyid(int id) {
    Book bookbyid = repository.findById(id).orElseThrow(()-> new NullPointerException("Book not found"+id));
    return bookbyid;
    }

    //delete implemented
    @Override
    public String deleteBook(int id) {
        repository.deleteById(id);
        return "Book deleted";
    }

    //update implemented
    @Override
    public Book updateBook(int id, Book newBook) {
        Book book = repository.findById(id).orElseThrow(()->new NullPointerException("Data not found"+id));

        book.setIsbn(newBook.getIsbn());
        book.setAvailableCopies(newBook.getAvailableCopies());

        Book updatedBook = repository.save(book);
        return updatedBook;
    }

    @Override
    public String saveExcelData(MultipartFile file) {

        try {
            List<Book> bookList = ExcelDataReadHelper.convertExcelDataToListBook(file.getInputStream());

            repository.saveAll(bookList);

            return "Excel Data uploaded successfully";
        }
        catch (IOException e){
            throw new RuntimeException("Failed to upload file"+e.getMessage());
        }
    }
}
