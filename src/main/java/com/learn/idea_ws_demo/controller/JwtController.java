package com.learn.idea_ws_demo.controller;

import com.learn.idea_ws_demo.entity.People;
import com.learn.idea_ws_demo.repository.PeopleRepository;
import com.learn.idea_ws_demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class JwtController {

    @Autowired
    PeopleRepository peopleRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(People people){
        String token = null;
        People people1 =
                peopleRepository.findByUsernameAndPassword(people.getUsername(), people.getPassword());
        if (people1 != null){
            token = JwtUtil.sign(people1.getUsername(), String.valueOf(people1.getId()));
        }
        return token;
    }
}
