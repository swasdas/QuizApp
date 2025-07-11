package com.swastik.quizapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor // Needed for Jackson
@AllArgsConstructor // Optional, useful for DTOs/tests
@Getter
@Setter
@ToString
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany
    private List<Question> questions;

}
