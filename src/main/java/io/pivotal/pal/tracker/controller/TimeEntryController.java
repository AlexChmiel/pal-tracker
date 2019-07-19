/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker.controller;

import io.pivotal.pal.tracker.data.TimeEntry;
import io.pivotal.pal.tracker.repository.TimeEntryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public ResponseEntity<?> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @RequestMapping(value = "/time-entries", method = RequestMethod.POST)
    public ResponseEntity<TimeEntry> timeEntries(@RequestBody TimeEntry timeEntry){
        TimeEntry n = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(n, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/time-entries", method = RequestMethod.GET)
    public ResponseEntity<List<TimeEntry>> timeEntriesList() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.GET)
    public ResponseEntity<TimeEntry> getTimeEntry(@PathVariable("id") Long id){
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TimeEntry> updateTimeEntry(@PathVariable("id") Long id, @RequestBody TimeEntry t){
        System.out.println("---------------------------Hitting update controller " + "id = " + id);
        return new ResponseEntity<>(timeEntryRepository.update(id, t), HttpStatus.OK);
    }

    @RequestMapping(value = "/time-entries/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTimeEntry(@PathVariable("id") Long id){
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<TimeEntry> create(TimeEntry timeEntry) {
        return new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    public ResponseEntity<TimeEntry> update(Long Id, TimeEntry timeEntry) {
        TimeEntry updatedEntry = timeEntryRepository.update(Id, timeEntry);
        if (updatedEntry != null) {
            return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedEntry, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<TimeEntry> read(Long id) {
        TimeEntry foundEntry = timeEntryRepository.find(id);
        if(foundEntry != null) {
            return new ResponseEntity<>(foundEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity delete(Long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
