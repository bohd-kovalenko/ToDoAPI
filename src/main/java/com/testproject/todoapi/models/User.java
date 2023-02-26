package com.testproject.todoapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false, unique = false)
    private String password;
    @Column(name = "creation_date", nullable = false, unique = false)
    @CreationTimestamp
    private LocalDateTime registrationDateTime;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}
