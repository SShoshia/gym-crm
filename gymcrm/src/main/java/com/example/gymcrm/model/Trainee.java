package com.example.gymcrm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String address;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

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
