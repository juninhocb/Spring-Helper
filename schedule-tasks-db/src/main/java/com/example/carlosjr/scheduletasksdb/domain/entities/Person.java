package com.example.carlosjr.scheduletasksdb.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Boolean enabled;
    private String email;
    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @Column(name = "schedule_time")
    private LocalTime scheduleTime;

}
