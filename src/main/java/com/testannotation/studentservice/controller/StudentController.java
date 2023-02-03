package com.testannotation.studentservice.controller;

import com.testannotation.studentservice.model.Student;
import com.testannotation.studentservice.model.StudentNotFoundException;
import com.testannotation.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student-service/student")
    public Student findStudentByID(@RequestParam String ID){
        return studentService.findStudentByID(ID).orElseThrow(()-> new StudentNotFoundException());
    }

    @PostMapping(value = "/student-service/student", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student){
        Student newStudent = studentService.addStudent(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("ID", newStudent.getID())
                .build().toUri();
        return ResponseEntity.created(location).body(newStudent);
    }
}
