package com.pinsoft.recipesite.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private Long id;
    private String name;
    private int preparation_time;
    private String base64Image;
    private String explanation;
    private String[] materials;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
