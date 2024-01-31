package com.project.zipkok.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "KokImage")
@Getter
@NoArgsConstructor
public class KokImage {

    @Id
    @Column(name ="kok_image_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long kokImageId;

    @Column(name ="image_url", nullable = false)
    private String imageUrl;

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = true)
    private Option option;

    @ManyToOne
    @JoinColumn(name = "kok_id", nullable = false)
    private Kok kok;

    public KokImage(String imageUrl, String category){
        this.imageUrl = imageUrl;
        this.category =category;
    }
}
