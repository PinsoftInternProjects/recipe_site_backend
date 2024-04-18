package com.pinsoft.recipesite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddDto {
    private Long userId;
    private Long recipeId;
    private String comment;
    private String base64image;
    private LocalDateTime time;
}
