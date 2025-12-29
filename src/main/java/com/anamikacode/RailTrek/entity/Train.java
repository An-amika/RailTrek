package com.anamikacode.RailTrek.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
@Entity
@Table(name = "train")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "route")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;  // trainId provided manually (not auto-generated)

    @Column(name = "train_name", nullable = false)
    private String trainName;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(nullable = false)
    private Double fare;
 // (fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "route_id")   // foreign key reference to Route table //owning side
    @JsonBackReference   // Prevents infinite recursion
//route  is parent
    private Route route; // train (Owning side have foreign key routeId , child entity has foreign key who owns table here is parent routeID  )
}


