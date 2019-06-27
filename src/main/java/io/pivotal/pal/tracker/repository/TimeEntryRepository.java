/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker.repository;

import io.pivotal.pal.tracker.data.TimeEntry;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

public interface TimeEntryRepository<T, ID extends Serializable>  extends Repository<T, ID> {
    TimeEntry find(Long id);
    TimeEntry create(TimeEntry timeEntry);
    TimeEntry update(Long id, TimeEntry timeEntry);
    List<TimeEntry> list();
    void delete(Long id);

}
