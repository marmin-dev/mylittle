package com.sumsum.ai.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Entity
@Table
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String author;

    @Lob
    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private String charMo;
}
