package com.testannotation.studentservice.service;

import com.testannotation.studentservice.model.Student;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class StudentService {

    private static List<Student> students = new ArrayList<>();

    static {
        Student student1 = new Student("John", "Smart", "2020091701", "02/02/1997", "04/12/2009", "15 Foreshore Road, Philadelphia, PA, 19101");
        Student student2 = new Student("Simon", "North", "2001110703", "01/02/1994", "07/11/2001", "25 Market Street, Philadelphia, PA, 19102");
        students.addAll(Arrays.asList(student1, student2));
    }
    public Optional<Student> findStudentByID(String ID) {
        Student studentFound = null;
        for(Student student : students){
            if(student.getID().equals(ID))
                studentFound = student;
        }
            return Optional.ofNullable(studentFound);
    }

    public Student addStudent(Student student){
        long id = (long) (Math.floor(Math.random() * (9*Math.pow(10, 9))) + Math.pow(10, 9));
        student.setID(Long.toString(id));
        students.add(student);
        return student;
    }
}
