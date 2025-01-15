package com.project.bookreview.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user", schema="bookreview")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, length = 70)
    private String fullName;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


}
