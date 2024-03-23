// Write your code here
package com.example.school.repository;

import com.example.school.model.*;
import java.util.*;

public interface StudentRepository {
    String addBulkStudents(ArrayList<Student> alist);

    void deleteStudent(int studentId);

    Student updateStudent(int studentId, Student sobj);

    Student getStudentById(int studentId);

    Student addStudent(Student sobj);

    ArrayList<Student> getStudents();
}