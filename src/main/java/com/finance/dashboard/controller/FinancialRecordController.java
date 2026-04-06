package com.finance.dashboard.controller;


import com.finance.dashboard.constants.RecordType;
import com.finance.dashboard.dto.ApiResponse;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.service.FinancialRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    @Autowired
    private FinancialRecordService financialRecordService;


    @PostMapping
    public FinancialRecord createRecord(@Valid @RequestBody FinancialRecord financialRecord){
        return financialRecordService.createRecord(financialRecord);
    }

    @GetMapping
    public List<FinancialRecord> getAllRecords(){
        return financialRecordService.getAllRecords();
    }

    @GetMapping("/user/{userId}")
    public List<FinancialRecord> getRecordsByUser(@PathVariable String userId){
        return financialRecordService.getRecordsByUser(userId);
    }

    @GetMapping("/filter")
    public List<FinancialRecord> getFilteredRecords(

            @RequestParam(required = false) String userId,
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) String category,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate
    ) {
        return financialRecordService.getFilteredRecords(userId, type, category, startDate, endDate);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<Page<FinancialRecord>>> getRecords(
            @RequestParam String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<FinancialRecord> records = financialRecordService.getRecords(userId, pageable);

        return ResponseEntity.ok(
                new ApiResponse<>("SUCCESS", "Records fetched", records)
        );
    }

}
