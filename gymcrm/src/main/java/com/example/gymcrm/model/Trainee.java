package com.example.gymcrm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
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

    public Long getUserId() {
        return user.getId();
    }
}
