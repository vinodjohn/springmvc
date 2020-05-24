package com.sda.studysystem.models;

import lombok.Data;

import javax.persistence.*;

/**
 * School model
 *
 * @author VinodJohn
 */
@Entity
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String street;
    @OneToOne
    private City city;
    private String zipcode;
    private boolean isActive;
}
