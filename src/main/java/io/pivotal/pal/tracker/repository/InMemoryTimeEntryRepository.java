/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker.repository;

import io.pivotal.pal.tracker.data.TimeEntry;

import org.springframework.stereotype.Service;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    protected Map<Long, TimeEntry> data = new HashMap<>();

    public TimeEntry find (Long id) {
        TimeEntry t = getStore().get(id);
        if(t != null){
            return t;
        }
        return null;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
         Long id = timeEntry.getId();
         if(id == 0L && !getStore().containsKey(1L)){
             id = 1L;
         } else{
             Long max = Collections.max(getStore().keySet());
             id = max + 1L;
         }
         timeEntry.setId(id);
         getStore().put(id, timeEntry);
         return getStore().get(id);
    }

    @Override
    public boolean delete(Long id) {
        if(find(id) != null){
            getStore().put(id, null);
            return true;
        }
        return false;
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if(getStore().containsKey(id)){
            timeEntry.setId(id);
            getStore().put(id, timeEntry);
            return getStore().get(id);
        }
        return null;
    }

    @Override
    public List<TimeEntry> list() {
       List<TimeEntry> timeEntryList = new ArrayList<>();
       Map<Long, TimeEntry> map = getStore();
       for(Map.Entry<Long,TimeEntry> record: map.entrySet() ){
           if(record.getValue() != null){
               timeEntryList.add(record.getValue());
           }
       }

       return timeEntryList;
    }

    @SuppressWarnings("UnsafeOperation")
    private Map<Long, TimeEntry> getStore(){
        return this.data;
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
