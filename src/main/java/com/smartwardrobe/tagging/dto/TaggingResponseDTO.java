package com.smartwardrobe.tagging.dto;

public class TaggingResponseDTO {
    private String color;
    private String fabric;
    private String pattern;
    private String suitableOccasions;

    public TaggingResponseDTO() {}

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getFabric() { return fabric; }
    public void setFabric(String fabric) { this.fabric = fabric; }

    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }

    public String getSuitableOccasions() { return suitableOccasions; }
    public void setSuitableOccasions(String suitableOccasions) { this.suitableOccasions = suitableOccasions; }
}
