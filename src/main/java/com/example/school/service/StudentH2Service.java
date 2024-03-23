/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

//  studentId	INTEGER
// studentName	TEXT
// gender	TEXT
// standard	INTEGER

// Write your code here
package com.example.school.service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;
import com.example.school.model.*;
import com.example.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class StudentH2Service implements StudentRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public String addBulkStudents(ArrayList<Student> alist) {
        for (Student x : alist) {
            db.update("insert into student(studentName,gender,standard) values(?,?,?)", x.getStudentName(),
                    x.getGender(), x.getStandard());
        }
        return String.format("Successfully added %d students", alist.size());
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("delete student where studentId=?", studentId);
    }

    @Override
    public Student updateStudent(int studentId, Student sobj) {
        if (sobj.getStudentName() != null)
            db.update("update student set studentName = ? where studentId=?", sobj.getStudentName(), studentId);
        if (sobj.getGender() != null)
            db.update("update student set gender=? where studentId=?", sobj.getGender(), studentId);
        if (sobj.getStandard() != 0)
            db.update("update student set standard=? where studentId=?", sobj.getStandard(), studentId);
        return getStudentById(studentId);
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            Student rstd = db.queryForObject("select * from student where studentId=?", new StudentRowMapper(),
                    studentId);
            return rstd;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student addStudent(Student sobj) {
        db.update("insert into student(studentName,gender,standard) values(?,?,?)", sobj.getStudentName(),
                sobj.getGender(), sobj.getStandard());
        Student nstd = db.queryForObject("select * from student where studentName=? and gender=? and standard=?",
                new StudentRowMapper(), sobj.getStudentName(), sobj.getGender(), sobj.getStandard());
        return nstd;
    }

    @Override
    public ArrayList<Student> getStudents() {
        List<Student> slist = db.query("select * from student", new StudentRowMapper());
        ArrayList<Student> rstudents = new ArrayList<>(slist);
        return rstudents;
    }
}
