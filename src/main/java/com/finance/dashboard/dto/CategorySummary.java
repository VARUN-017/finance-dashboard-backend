package com.finance.dashboard.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorySummary {

    private String category;
    private double totalAmount;
}