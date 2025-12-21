package com.catlog.serviceImpl;

import com.catlog.dto.DashboardSummaryDTO;
import com.catlog.repository.BookCategoryRepo;
import com.catlog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookCategoryRepo bookCategoryRepo;

    public DashboardService(BookRepository bookRepository, BookCategoryRepo bookCategoryRepo) {
        this.bookRepository = bookRepository;
        this.bookCategoryRepo = bookCategoryRepo;
    }

    public DashboardSummaryDTO getDashboardSummary() {

        long totalBooks = bookRepository.count();
        long outOfStock = bookRepository.countByAvailableCopies(0);
        long totalCategories = bookCategoryRepo.count();
        int totalCopies = bookRepository.getTotalCopies() != null
                ? bookRepository.getTotalCopies()
                : 0;

        return new DashboardSummaryDTO(
                totalBooks,
                outOfStock,
                totalCategories,
                totalCopies
        );
    }
}
