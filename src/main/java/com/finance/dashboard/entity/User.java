package com.finance.dashboard.entity;


import com.finance.dashboard.constants.Role;
import com.finance.dashboard.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String id;

    private String userName;

    private String email;

    private Role role;

    private Status status;

}
