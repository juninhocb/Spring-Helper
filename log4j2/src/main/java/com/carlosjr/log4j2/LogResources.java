package com.carlosjr.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogResources {
    private static final Logger logger = LogManager.getLogger(LogResources.class);
    @GetMapping("/debug")
    public ResponseEntity<Void> produceDebugLog(){
        logger.debug("Debug log produced.");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/info")
    public ResponseEntity<Void> produceInfoLog(){
        logger.info("Info log produced.");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/warn")
    public ResponseEntity<Void> produceWarnLog(){
        logger.warn("Warn log produced.");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/error")
    public ResponseEntity<Void> produceErrorLog(){
        logger.error("Error log produced.");
        return ResponseEntity.ok().build();
    }



}
