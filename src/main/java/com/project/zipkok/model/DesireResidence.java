package com.project.zipkok.model;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DesireResidence")
@NoArgsConstructor
@Getter
public class DesireResidence {

    @Id
    @Column(name = "desire_residence_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long desireResidenceId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
