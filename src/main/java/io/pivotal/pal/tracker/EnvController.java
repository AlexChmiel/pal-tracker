/*
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {
    private String port;
    @Value("${cf.memory.limit:NOT SET}")
    private String memoryLimit;
    @Value("${cf.instance.index:NOT SET}")
    private String cfInstanceIndex;
    @Value("${cf.instance.addr:NOT SET}")
    private String cfInstanceAddr;

    public EnvController(
            @Value("${cf.port:NOT SET}") String port,
            @Value("${cf.memory.limit:NOT SET}") String memoryLimit,
            @Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,
            @Value("${cf.instance.addr:NOT SET}") String cfInstanceAddr) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddr = cfInstanceAddr;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getCfInstanceIndex() {
        return cfInstanceIndex;
    }

    public void setCfInstanceIndex(String cfInstanceIndex) {
        this.cfInstanceIndex = cfInstanceIndex;
    }

    public String getCfInstanceAddr() {
        return cfInstanceAddr;
    }

    public void setCfInstanceAddr(String cfInstanceAddr) {
        this.cfInstanceAddr = cfInstanceAddr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv(){
        Map<String, String> envMap = new HashMap<>();
        envMap.put("PORT", getPort());
        envMap.put("MEMORY_LIMIT", getMemoryLimit());
        envMap.put("CF_INSTANCE_INDEX", getCfInstanceIndex());
        envMap.put("CF_INSTANCE_ADDR", getCfInstanceAddr());

        return envMap;
    }
}
