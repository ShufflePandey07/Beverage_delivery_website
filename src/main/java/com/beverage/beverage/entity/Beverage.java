package com.beverage.beverage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "beverage")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Beverage {

    @Id
    @SequenceGenerator(name = "beverage_seq_gen", sequenceName = "beverage_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "beverage_seq_gen", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "beverage_name", length = 100, nullable = false)
    private String name;

    @Column(name = "beverage_description", length = 1000, nullable = false)
    private String description;

    @Column(name = "beverage_price", nullable = false)
    private double price;

    @Column(name = "beverage_image", length = 1000, nullable = false)
    private String image;

    @Transient
    private String imageBase64;

    @ManyToOne
    private Category category;

}