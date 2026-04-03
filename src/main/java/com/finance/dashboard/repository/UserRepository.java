package com.finance.dashboard.repository;

import com.finance.dashboard.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {



}
