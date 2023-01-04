package com.yearup.conferencedemo.controllers;

import com.yearup.conferencedemo.models.Session;
import com.yearup.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsControllers {
    @Autowired
    private SessionRepository sessionRepository;
    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }
    @PostMapping //creates a session obj
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session); //Saves the object
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // overrides dependency between the children
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT) //Updates the records
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

}
