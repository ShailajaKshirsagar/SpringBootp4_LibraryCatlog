package com.catlog.repository;

import com.catlog.entity.BookDetailsMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailsRepo extends JpaRepository <BookDetailsMapping, Integer>
{

}
