package com.example.tareagtics20202396.Models.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @Column(name = "location_id",nullable = false)
    private Integer locationId;

    @Column(name = "city",nullable = false)
    private  String cityName;
}
