package com.catlog.repository;

import com.catlog.entity.BookReviewMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewRepo extends JpaRepository<BookReviewMapping, Integer> {
}
