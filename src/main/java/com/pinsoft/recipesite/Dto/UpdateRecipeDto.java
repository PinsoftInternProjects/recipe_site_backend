package com.pinsoft.recipesite.Dto;

import lombok.Data;

@Data
public class UpdateRecipeDto {
    private Long id;
    private String name;
    private int preparation_time;
    private String base64image;
    private String explanation;
    private String[] materials;
    private Long categoryId;
    private Long user_id;
}
