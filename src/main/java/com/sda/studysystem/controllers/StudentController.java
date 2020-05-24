package com.sda.studysystem.controllers;

import com.sda.studysystem.models.Student;
import com.sda.studysystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle Student requests
 *
 * @author VinodJohn
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Student> showAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(Student student) {
        studentService.createStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentId, Student student) {
        student.setId(studentId);
        studentService.updateStudent(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreStudent(@PathVariable("id") Long studentId) {
        studentService.restoreStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


