package com.mayank.employwise_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mayank.employwise_backend.entity.Employee;

@Repository
public interface EmployRepository extends MongoRepository<Employee,String> {

}
