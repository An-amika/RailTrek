package com.anamikacode.RailTrek.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="route")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq")
   //@SequenceGenerator(name = "route_seq", sequenceName = "route_seq", initialValue = 100, allocationSize = 1)


    @Column(nullable = false)
    private String source;


    @Column(nullable = false)
    private String destination;
//orphanRemoval = true,
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JsonManagedReference   //Parent side, route act as parent(without route there is no train)
    private List<Train> trainList = new ArrayList<>(); // route is here inversion side mapped by own self there is no foreign key
}
