package com.example.schoolservice.repository;

import com.example.schoolservice.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
