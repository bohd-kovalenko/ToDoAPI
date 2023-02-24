package com.testproject.todoapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "is_done", nullable = false, unique = false)
    private boolean isDone;
    @Column(name = "creation_date", nullable = false, unique = false, columnDefinition = "timestamp not null default current_timestamp")
    @CreationTimestamp
    private LocalDateTime creationDate;
}
