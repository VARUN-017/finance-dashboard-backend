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

    public List<FinancialRecord> filteredRecords(String userId, RecordType type) {

        if (userId != null && type != null) {
            return financialRecord.findByUserIdAndType(userId, type);
        }

        if (userId != null) {
            return financialRecord.findByUserId(userId);
        }

        return financialRecord.findAll();
    }

}
