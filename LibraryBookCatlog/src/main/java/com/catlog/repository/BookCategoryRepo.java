package com.catlog.repository;

import com.catlog.entity.BookCategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepo extends JpaRepository<BookCategoryMapping,Integer>
{
}
