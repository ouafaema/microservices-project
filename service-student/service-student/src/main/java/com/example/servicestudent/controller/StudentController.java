package com.example.servicestudent.controller;

import com.example.servicestudent.model.Student;
import com.example.servicestudent.service.SchoolServiceClient;
import com.example.servicestudent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolServiceClient schoolServiceClient;

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getStudentById(@PathVariable String id) {
        var student = studentService.getStudentById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        String schoolDetails = schoolServiceClient.getSchoolById(student.getSchoolId());

        Map<String, Object> response = new HashMap<>();
        response.put("student", student);
        response.put("school", schoolDetails);

        return response;
    }
}
