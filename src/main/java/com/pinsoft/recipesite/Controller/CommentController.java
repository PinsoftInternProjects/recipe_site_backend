package com.pinsoft.recipesite.Controller;

import com.pinsoft.recipesite.DTO.CommentAddDto;
import com.pinsoft.recipesite.DTO.CommentGetDto;
import com.pinsoft.recipesite.DTO.CommentUpdateDto;
import com.pinsoft.recipesite.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public boolean AddComment(@RequestBody CommentAddDto commentAddDto) {
        return commentService.AddComment(commentAddDto);
    }

    @GetMapping("/comment")
    public List<CommentGetDto> GetAllComments(){
        return commentService.GetAllComments();
    }

    @GetMapping("/comment/{recipeId}")
    public List<CommentGetDto> GetCommentsByRecipeId(@PathVariable Long recipeId){
        return commentService.GetCommentByRecipeId(recipeId);
    }

    @DeleteMapping("/comment/{commentId}")
    public boolean RemoveComment(@PathVariable Long commentId){
        return commentService.RemoveComment(commentId);
    }

    @PutMapping("/comment/{id}")
    public boolean UpdateComment(@RequestBody CommentUpdateDto commentUpdateDto, @PathVariable Long id){
        return commentService.UpdateComment(commentUpdateDto,id);
    }
}
