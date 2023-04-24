package com.sumsum.ai.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Entity
//@NoArgsConstructor
@Table
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String question;

    @NonNull
    private String author;
}
