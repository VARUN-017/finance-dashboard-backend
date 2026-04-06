package com.finance.dashboard.service;

import com.finance.dashboard.constants.RecordType;
import com.finance.dashboard.dto.CategorySummary;
import com.finance.dashboard.dto.DashboardSummary;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.repository.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    public FinancialRecordRepository recordRepository;

    public DashboardSummary getSummary() {

        List<FinancialRecord> records = recordRepository.findAll();

        double totalIncome = records.stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double totalExpense = records.stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        return DashboardSummary.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .netBalance(totalIncome - totalExpense)
                .build();
    }

    public List<CategorySummary> getCategorySummary() {

        List<FinancialRecord> records = recordRepository.findAll();

        Map<String, Double> grouped = records.stream()
                .collect(Collectors.groupingBy(
                        FinancialRecord::getCategory,
                        Collectors.summingDouble(FinancialRecord::getAmount)
                ));

        return grouped.entrySet().stream()
                .map(entry -> new CategorySummary(entry.getKey(), entry.getValue()))
                .toList();
    }

}
