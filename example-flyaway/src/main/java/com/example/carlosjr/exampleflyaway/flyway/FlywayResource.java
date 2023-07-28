package com.example.carlosjr.exampleflyaway.flyway;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/migrate")
public class FlywayResource {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String migrateToDatabase(@RequestParam(name = "database") String dbName){

        String datasourceUrl = String.format("jdbc:mysql://localhost:3306/%s", dbName);

        Flyway flywayInstance = Flyway
                .configure()
                .dataSource(datasourceUrl, "root", "root")
                .load();

        MigrateResult migrateResult =  flywayInstance.migrate();

        if (migrateResult.migrationsExecuted > 0){
            return "Success! " + migrateResult.migrationsExecuted + " was executed.";
        }

        return "No migrations to do";

    }

}
