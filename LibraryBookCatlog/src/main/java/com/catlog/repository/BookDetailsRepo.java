package com.catlog.repository;

import com.catlog.entity.BookDetailsMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepo extends JpaRepository <BookDetailsMapping, Integer>
{

}
