package com.pinsoft.recipesite.Repository;

import com.pinsoft.recipesite.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentByRecipeId(Long id);
}
