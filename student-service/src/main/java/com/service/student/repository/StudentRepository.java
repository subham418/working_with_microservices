package com.service.student.repository;

import com.service.student.entity.students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<students,String> {
    // all crud methods here
}
