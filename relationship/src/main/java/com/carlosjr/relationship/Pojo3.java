package com.carlosjr.relationship;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pojo3 {
    @Id
    @GeneratedValue
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private UUID id;
    private String name;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pojo4_id", referencedColumnName = "id")
    private Pojo4 pojo4;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pojo1_id", referencedColumnName = "id")
    private Pojo1 pojo1;

}
