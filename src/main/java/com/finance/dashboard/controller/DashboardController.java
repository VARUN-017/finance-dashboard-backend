package com.finance.dashboard.controller;

import com.finance.dashboard.dto.*;
import com.finance.dashboard.service.DashboardService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<DashboardSummary>> getSummary() {

        DashboardSummary summary = service.getSummary();

        ApiResponse<DashboardSummary> response = ApiResponse.<DashboardSummary>builder()
                .status("SUCCESS")
                .message("Dashboard summary fetched successfully")
                .data(summary)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<CategorySummary>>> getCategorySummary() {

        List<CategorySummary> data = service.getCategorySummary();

        ApiResponse<List<CategorySummary>> response = ApiResponse.<List<CategorySummary>>builder()
                .status("SUCCESS")
                .message("Category summary fetched successfully")
                .data(data)
                .build();

        return ResponseEntity.ok(response);
    }
}