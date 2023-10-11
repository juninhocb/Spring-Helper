package org.example.model;

import java.io.Serializable;

public class Stub implements Serializable {
    final long serialVersionUID = 1L;
    private Integer id; private String name; private Boolean isAny;
    public Stub(Integer id, String name, Boolean isAny  ){ this.id = id; this.name = name; this.isAny = isAny; }

    @Override
    public String toString() {
        return "Stub{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", isAny=" + this.isAny +
                '}';
    }
}

