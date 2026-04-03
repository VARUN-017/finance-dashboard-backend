package com.finance.dashboard.controller;


import com.finance.dashboard.constants.RecordType;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.service.FinancialRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(required = false) RecordType type) {

        return financialRecordService.filteredRecords(userId, type);
    }

}
