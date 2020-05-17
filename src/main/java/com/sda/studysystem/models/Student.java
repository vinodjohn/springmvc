package com.sda.studysystem.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Student model
 *
 * @author VinodJohn
 */

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate joinDate;
    private boolean isActive;
    @OneToOne
    private School school;
    private int grade;
}
