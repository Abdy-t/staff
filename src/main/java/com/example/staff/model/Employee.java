package com.example.staff.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank
    private String surname;

    @Column
    @NotBlank
    private String name;

    @Column
    private String patronymic;

    @Column(name = "date_of_birth")
    @NotBlank
    private LocalDate dateOfBirth;

    @Column(name = "date_of_hiring")
    @NotBlank
    private LocalDate dateOfHiring;

    @Column
    private String position;

    @Column
    private Integer manager_id;

}


