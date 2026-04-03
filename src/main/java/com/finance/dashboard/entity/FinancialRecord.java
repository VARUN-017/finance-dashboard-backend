package com.finance.dashboard.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finance.dashboard.constants.RecordType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;



@Document(collection = "financial_records")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialRecord {

    @Id
    private String id;

    private String userId;

    @NotNull
    @Positive
    private Double amount;

    @NotBlank
    private String category;

    @NotNull
    private RecordType type;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime transactionDate;

    private Boolean isDeleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
