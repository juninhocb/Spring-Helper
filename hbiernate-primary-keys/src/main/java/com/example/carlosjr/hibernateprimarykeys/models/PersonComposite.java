package com.example.carlosjr.hibernateprimarykeys.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonComposite {

    @EmbeddedId
    private NameId nameId;
    private String name;

}
