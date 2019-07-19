/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.repository.JdbcTimeEntryRepository;
import io.pivotal.pal.tracker.repository.TimeEntryRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
import java.util.TimeZone;

@SpringBootApplication
public class PalTrackerApplication {

    public static void main(String[] args){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(PalTrackerApplication.class, args);

    }

    @Bean
    public TimeEntryRepository repositoryService(DataSource dataSource) {
        return new JdbcTimeEntryRepository(dataSource);
    }
}
