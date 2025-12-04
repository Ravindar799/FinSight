package com.finsight.finsight.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // every expense must have a name
    private String name;

    @Column(nullable = false)
    private Double amount;

    private LocalDate date;

    private String paymentMode;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
