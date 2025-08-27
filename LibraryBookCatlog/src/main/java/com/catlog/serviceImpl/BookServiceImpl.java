package com.catlog.serviceImpl;

import com.catlog.entity.Book;
import com.catlog.entity.BookCategoryMapping;
import com.catlog.entity.BookReviewMapping;
import com.catlog.helper.ExcelDataReadHelper;
import com.catlog.helper.GenerateExcelFromDB;
import com.catlog.repository.BookRepository;
import com.catlog.service.BookService;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

        for(BookReviewMapping review : book.getReviews()){
            review.setBook(book);
        }
        // Optional: set Book for each Category's book list if bidirectional
        if (book.getCategories() != null) {
            for (BookCategoryMapping category : book.getCategories()) {
                category.getBooks().add(book); // Ensure bidirectional consistency
            }
        }

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

    @Override
    //password protected file
    public ByteArrayInputStream exportDataInExcel(String password) throws IOException {
        List<Book> books = repository.findAll();

        //create excel file
        ByteArrayInputStream normalExcel = GenerateExcelFromDB.bookToExcel(books);

        //Encrypt with password
        POIFSFileSystem fs = new POIFSFileSystem();
        EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
        Encryptor encryptor = info.getEncryptor();
        encryptor.confirmPassword("Book123"); //set password here

        try (OPCPackage opc = OPCPackage.open(normalExcel);
             OutputStream os = encryptor.getDataStream(fs)) {
            opc.save(os);
        } catch (Exception e) {
            throw new IOException("Error encrypting Excel file", e);
        }

        // Step 3: Return encrypted file as ByteArrayInputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        fs.writeFilesystem(bos);
        return new ByteArrayInputStream(bos.toByteArray());
    }
}
