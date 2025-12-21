package com.catlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardSummaryDTO {

    private long totalBooks;
    private long outOfStockBooks;
    private long totalCategories;
    private int totalCopies;
}

