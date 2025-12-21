package com.catlog.controller;

import com.catlog.dto.DashboardSummaryDTO;
import com.catlog.entity.Book;
import com.catlog.service.BookService;
import com.catlog.serviceImpl.DashboardService;
import org.aspectj.weaver.ast.Literal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/bookCatlog")
public class BookController {
    //inject service in this
    @Autowired
    private BookService bookService;
    @Autowired
    private DashboardService dashboardService;

    //add data api
    @PostMapping("/addBook")
    public ResponseEntity<String> addBookData(@RequestBody Book book) {
        System.err.println(book);
        bookService.saveBook(book);
        return new ResponseEntity<>("Book added", HttpStatus.CREATED);
    }

    //get all data api
    @GetMapping("/getAllBookData")
    public ResponseEntity<List<Book>> getAllData() {
        List<Book> bookList = bookService.getAllBook();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    //get Data byt id api
    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        Book book = bookService.getBookbyid(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    //delete by id api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }

    //update api
    @PutMapping("/updateData/{id}")
    public ResponseEntity<Book> updateData(@PathVariable("id") int id, @RequestBody Book book) {
        Book updateBook = bookService.updateBook(id, book);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    //Upload excel file
    @PostMapping(value = "/uploadExcel", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload excel file");
        } else {
            String msg = bookService.saveExcelData(file);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }

    //API To add generate excel file from database data
    @GetMapping("/downloadFile")
    public ResponseEntity<InputStreamSource> downloadFile() throws IOException{

        List<Book> bookList = bookService.getAllBook();
        if(bookList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .header("Message","No content in excel file ").build();
        }

        String password = "Book123";
        ByteArrayInputStream in  = bookService.exportDataInExcel(password);

        if (in == null || in.available() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=BookData.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }

    //summary api
    @GetMapping("/dashboard-summary")
    public DashboardSummaryDTO getDashboardSummary() {
        return dashboardService.getDashboardSummary();
    }

}
