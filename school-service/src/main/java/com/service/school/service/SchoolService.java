package com.service.school.service;

import com.service.school.entity.School;
import com.service.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository repository;
    public School addSchool(School school){
        System.out.println("Data received in Service: "+school);
        return repository.save(school);
    }
    public School fetchSchoolById(int id){
        return repository.findById(id).orElse(null);
    }
    public List<School> fetchSchools(){
        return repository.findAll();
    }
    public School deleteSchool(int id){
        School school=fetchSchoolById(id);
        repository.delete(school);
        return school;
    }
}
