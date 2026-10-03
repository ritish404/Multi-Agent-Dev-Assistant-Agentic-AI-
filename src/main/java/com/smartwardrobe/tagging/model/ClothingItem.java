package com.smartwardrobe.tagging.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clothing_items")
public class ClothingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
    private String fabric;
    private String pattern;
    private String suitableOccasions;
    private String imageUrl;

    public ClothingItem() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getFabric() { return fabric; }
    public void setFabric(String fabric) { this.fabric = fabric; }

    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }

    public String getSuitableOccasions() { return suitableOccasions; }
    public void setSuitableOccasions(String suitableOccasions) { this.suitableOccasions = suitableOccasions; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
