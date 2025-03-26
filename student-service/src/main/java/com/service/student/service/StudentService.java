package com.service.student.service;

import com.service.student.dao.School;
import com.service.student.dao.StudentResponse;
import com.service.student.entity.students;
import com.service.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    public ResponseEntity<?> createStudent(students student){
        try {
            return new ResponseEntity<students>(repository.save(student),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> fetchStudentById(String id){
        Optional<students> student =  repository.findById(id);
        if(student.isPresent()){
            School school = restTemplate.getForObject("http://SCHOOL-SERVICE/school/" + student.get().getSchoolId(), School.class);
            StudentResponse studentResponse = new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school
            );
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Student Found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> fetchStudents(){
        List<students> students = repository.findAll();
        if(students.size() > 0){
            return new ResponseEntity<List<students>>(students, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No Students",HttpStatus.NOT_FOUND);
        }
    }
}
