package com.example.gymcrm.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String specialization;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Training> trainings;

    public Long getUserId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String toString() {
        return "{ Trainer - Id: " + id + ", user id: " + user.getId() + ", specialization: " + specialization + " }";
    }
}
