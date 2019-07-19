/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker.repository;

import io.pivotal.pal.tracker.data.TimeEntry;

import java.util.List;

public interface TimeEntryRepository {
    TimeEntry find(Long id);
    TimeEntry create(TimeEntry timeEntry);
    TimeEntry update(Long id, TimeEntry timeEntry);
    List<TimeEntry> list();
    void delete(Long id);

}
