/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker.repository;

import io.pivotal.pal.tracker.data.TimeEntry;

import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface TimeEntryRepository<T, ID extends Serializable>  extends Repository {
    TimeEntry find(Long id);
    TimeEntry create(TimeEntry timeEntry);
    TimeEntry update(Long id, TimeEntry timeEntry);
    List<TimeEntry> list();
    boolean delete(Long id);

}
