package com.pinsoft.recipesite.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentGetDto {
    private Long id;
    private Long recipeeId;
    private Long userId;
    private String userName;
    private String comment;
    private String base64image;
    private LocalDateTime time;
}
