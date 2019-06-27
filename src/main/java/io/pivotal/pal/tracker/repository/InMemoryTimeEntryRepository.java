/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker.repository;

import io.pivotal.pal.tracker.data.TimeEntry;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long, TimeEntry> data = new HashMap<>();

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntry find (Long id) {
        return getStore().get(id);
        //return timeEntryRepository.find(id);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        //return timeEntryRepository.create(timeEntry);
         getStore().putIfAbsent(timeEntry.getId(), timeEntry);
         return getStore().get(timeEntry.getId());
    }

    @Override
    public void delete(Long id) {
        getStore().remove(id);
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry obj = getStore().get(id);
        getStore().replace(id, timeEntry);
        return find(id);
    }

    @Override
    public List<TimeEntry> list() {
       List<TimeEntry> timeEntryList = new ArrayList<>();
       getStore().forEach((k,v) -> timeEntryList.add(v));
       return timeEntryList;
    }

    @SuppressWarnings("UnsafeOperation")
    private Map<Long, TimeEntry> getStore(){
        return this.data;
    }
}
