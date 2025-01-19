package com.example.gymcrm.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String address;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Training> trainings;

    public Long getUserId() {
        return user.getId();
    }

    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String toString() {
        return "{ Trainee - Id: " + id + ", user id: " + user.getId() + ", address: " + address + ", date of birth: " + dateOfBirth + " }";
    }
}
