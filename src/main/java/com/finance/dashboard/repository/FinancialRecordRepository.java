package com.finance.dashboard.repository;

import com.finance.dashboard.constants.RecordType;
import com.finance.dashboard.entity.FinancialRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FinancialRecordRepository extends MongoRepository<FinancialRecord,String> {

    List<FinancialRecord> findByUserId(String userId);

    List<FinancialRecord> findByUserIdAndType(String userId, RecordType type);

    List<FinancialRecord> findByTransactionDateBetween(LocalDateTime start, LocalDateTime end);
}
