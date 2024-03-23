/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.school.service.*;
import com.example.school.model.*;
import java.util.*;

@RestController
public class StudentController {
    @Autowired
    public StudentH2Service sservice;

    @PostMapping("/students/bulk")
    public String addBulkStudents(@RequestBody ArrayList<Student> alist) {
        return sservice.addBulkStudents(alist);
    }

    @DeleteMapping("students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        sservice.deleteStudent(studentId);
    }

    @PutMapping("students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student sobj) {
        return sservice.updateStudent(studentId, sobj);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return sservice.getStudentById(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student sobj) {
        return sservice.addStudent(sobj);
    }

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return sservice.getStudents();
    }
}