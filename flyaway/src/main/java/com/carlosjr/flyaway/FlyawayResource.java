package com.carlosjr.flyaway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.flywaydb.core.Flyway;

@RestController
@RequestMapping(value = "/flyaway")
public class FlyawayResource {

    @GetMapping(value = "/{schema}")
    public ResponseEntity<Void> updateSchema(@PathVariable String schema){
        String url = String.format("jdbc:mysql://localhost:3306/%s", schema);
        Flyway flyway = Flyway
                .configure()
                .baselineVersion("1")
                .baselineOnMigrate(true)
                .dataSource(url, "root", "root")
                .load();
        flyway.baseline();
        flyway.migrate();
        return ResponseEntity.ok().build();
    }

}
