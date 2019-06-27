/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.repository.InMemoryTimeEntryRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PalTrackerApplication {

    public static void main(String[] args){
        SpringApplication.run(PalTrackerApplication.class, args);

    }
    @Bean
    public InMemoryTimeEntryRepository repositoryService() {
        return new InMemoryTimeEntryRepository();
    }
}
