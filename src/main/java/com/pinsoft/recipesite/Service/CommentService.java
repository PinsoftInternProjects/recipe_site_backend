package com.pinsoft.recipesite.Service;

import com.pinsoft.recipesite.DTO.CommentAddDto;
import com.pinsoft.recipesite.DTO.CommentGetDto;
import com.pinsoft.recipesite.DTO.CommentUpdateDto;
import com.pinsoft.recipesite.Entity.Comment;
import com.pinsoft.recipesite.Repository.CommentRepository;
import com.pinsoft.recipesite.Repository.RecipeRepository;
import com.pinsoft.recipesite.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean AddComment(CommentAddDto commentAddDto){
        try {
            Comment comment = new Comment();
            comment.setUser(userRepository.findById(commentAddDto.getUserId()).get());
            comment.setRecipe(recipeRepository.findById(commentAddDto.getRecipeId()).get());
            comment.setComment(commentAddDto.getComment());
            comment.setTime(commentAddDto.getTime());
            comment.setBase64image(commentAddDto.getBase64image());
            commentRepository.save(comment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<CommentGetDto> GetAllComments(){
        List<Comment> comments = commentRepository.findAll();
        List<CommentGetDto> commentGetDtoList = new ArrayList<>();
        comments.forEach(comment -> {
            CommentGetDto commentGetDto = new CommentGetDto();
            commentGetDto.setId(comment.getId());
            commentGetDto.setRecipeeId(comment.getRecipe().getId());
            commentGetDto.setUserId(comment.getUser().getId());
            commentGetDto.setUserName(comment.getUser().getUsername());
            commentGetDto.setComment(comment.getComment());
            commentGetDto.setTime(comment.getTime());
            commentGetDto.setBase64image(comment.getBase64image());
            commentGetDtoList.add(commentGetDto);
        });
        return commentGetDtoList;
    }

    public List<CommentGetDto> GetCommentByRecipeId(Long id){
        List<Comment> comments = commentRepository.findCommentByRecipeId(id);
        List<CommentGetDto> commentGetDtoList = new ArrayList<>();
        comments.forEach(comment -> {
            CommentGetDto commentGetDto = new CommentGetDto();
            commentGetDto.setId(comment.getId());
            commentGetDto.setRecipeeId(comment.getRecipe().getId());
            commentGetDto.setUserId(comment.getUser().getId());
            commentGetDto.setUserName(comment.getUser().getUsername());
            commentGetDto.setComment(comment.getComment());
            commentGetDto.setTime(comment.getTime());
            commentGetDto.setBase64image(comment.getBase64image());
            commentGetDtoList.add(commentGetDto);
        });
        return commentGetDtoList;
    }

    public boolean RemoveComment(Long id){
        try {
            commentRepository.deleteById(id);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public boolean UpdateComment(CommentUpdateDto commentUpdateDto,Long id){
        try {
            Comment comment = commentRepository.findById(id).get();
            comment.setComment(commentUpdateDto.getComment());
            comment.setBase64image(commentUpdateDto.getBase64image());
            comment.setTime(commentUpdateDto.getTime());
            commentRepository.save(comment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
