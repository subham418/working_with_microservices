package com.service.school.controller;

import com.service.school.entity.School;
import com.service.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolService service;
    @GetMapping("/all")
    public List<School> getAllSchool(){
        return service.fetchSchools();
    }
    @GetMapping("/{id}")
    public School getById(@PathVariable int id){
        return service.fetchSchoolById(id);
    }
    @PostMapping("/save")
    public School saveSchool(@RequestBody School school){
        System.out.println("Data received in controller: "+school);
        return service.addSchool(school);
    }
    @DeleteMapping("/{id}")
    public School deleteById(@PathVariable int id){
        return service.deleteSchool(id);
    }
}
