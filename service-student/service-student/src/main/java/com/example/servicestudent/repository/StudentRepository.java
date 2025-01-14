package com.example.servicestudent.repository;

import com.example.servicestudent.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
