package com.example.gymcrm.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    private Long id;
    private Long userId;
    private String specialization;
}
