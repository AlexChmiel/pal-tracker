/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker.controller;

import io.pivotal.pal.tracker.data.TimeEntry;
import io.pivotal.pal.tracker.repository.TimeEntryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/time-series")
    public ResponseEntity<TimeEntry> timeEntries(@RequestBody TimeEntry timeEntry){
        return create(timeEntry);
    }

    public ResponseEntity<TimeEntry> create(TimeEntry timeEntry) {
        return new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    public ResponseEntity<TimeEntry> update(Long Id, TimeEntry timeEntry) {
        TimeEntry updatedEntry = timeEntryRepository.update(Id, timeEntry);
        if (updatedEntry != null) {
            return new ResponseEntity<>(timeEntryRepository.update(Id, timeEntry), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<TimeEntry> read(Long id) {
        TimeEntry foundEntry = timeEntryRepository.find(id);
        if(foundEntry != null) {
            return new ResponseEntity<>(foundEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity delete(Long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if (timeEntry != null) {
            timeEntryRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
