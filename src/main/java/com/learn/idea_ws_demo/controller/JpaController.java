package com.learn.idea_ws_demo.controller;

import com.learn.idea_ws_demo.entity.People;
import com.learn.idea_ws_demo.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class JpaController {
    @Autowired
    PeopleRepository peopleRepository;

    @RequestMapping("/people/{id}")
    public People selPeople(@PathVariable("id") Integer id){
        People people = peopleRepository.findOne(id);
        return people;
    }

    @RequestMapping("/people")
    public People insPeople(People people){
        People people1 = peopleRepository.save(people);
        return people1;
    }
}
