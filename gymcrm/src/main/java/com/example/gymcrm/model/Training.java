package com.example.gymcrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainee_id", referencedColumnName = "id")
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    private String trainingName;

    private String trainingType;

    @Temporal(TemporalType.DATE)
    private Date trainingDate;

    private int trainingDuration;

    public Long getTraineeId() {
        return trainee.getId();
    }

    public Long getTrainerId() {
        return trainer.getId();
    }
}
