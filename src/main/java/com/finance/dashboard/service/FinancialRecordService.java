package com.finance.dashboard.service;

import com.finance.dashboard.constants.RecordType;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.repository.FinancialRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository financialRecord;

    public FinancialRecord createRecord(FinancialRecord record){

        if (record.getTransactionDate() == null) {
            record.setTransactionDate(LocalDateTime.now());
        }

        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());
        record.setIsDeleted(false);
        return financialRecord.save(record);
    }

    public List<FinancialRecord> getAllRecords(){
        return financialRecord.findAll();
    }

    public List<FinancialRecord> getRecordsByUser(String userId){
        return financialRecord.findByUserId(userId);
    }

    public List<FinancialRecord> getFilteredRecords(
            String userId,
            RecordType type,
            String category,
            LocalDateTime startDate,
            LocalDateTime endDate) {

        List<FinancialRecord> records = financialRecord.findAll();

        if (userId != null) {
            records = records.stream()
                    .filter(r -> userId.equals(r.getUserId()))
                    .toList();
        }

        if (type != null) {
            records = records.stream()
                    .filter(r -> type.equals(r.getType()))
                    .toList();
        }

        if (category != null) {
            records = records.stream()
                    .filter(r -> category.equalsIgnoreCase(r.getCategory()))
                    .toList();
        }

        if (startDate != null && endDate != null) {
            records = records.stream()
                    .filter(r -> {
                        LocalDateTime txnDate = r.getTransactionDate();
                        return txnDate != null &&
                                !txnDate.isBefore(startDate) &&
                                !txnDate.isAfter(endDate);
                    })
                    .toList();
        }

        return records;
    }

}
