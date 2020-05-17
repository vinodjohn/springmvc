package com.sda.studysystem.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Specialized Field model
 *
 * @author VinodJohn
 */
@Entity
@Data
public class SpecializedField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne
    private Category category;
}
