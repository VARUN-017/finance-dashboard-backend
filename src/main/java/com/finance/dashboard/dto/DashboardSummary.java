package com.finance.dashboard.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardSummary {

    private double totalIncome;
    private double totalExpense;
    private double netBalance;
}