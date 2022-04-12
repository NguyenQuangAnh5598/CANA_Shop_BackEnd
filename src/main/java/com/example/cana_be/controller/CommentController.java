package com.example.cana_be.controller;

import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.Comment;
import com.example.cana_be.model.User;
import com.example.cana_be.security.userprincal.UsersDetailService;
import com.example.cana_be.service.extend.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService commentService;


    @Autowired
    UsersDetailService usersDetailService;

    @PostMapping
    public ResponseEntity<?> createNewComment(@RequestBody Comment comment) {
        commentService.save(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/showAllComment")
    public ResponseEntity<?> findAllCommentByProductId(@RequestBody String id) {
        Long newId =  Long.parseLong(id);
        List<Comment> commentList = commentService.findAllByProductId(newId);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        User user = usersDetailService.getCurrentUser();
        if(!commentOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("NOT"), HttpStatus.NOT_FOUND);
        } else if(commentOptional.get().getUser().getId() == user.getId()) {
            commentService.remove(id);
            return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("NO"), HttpStatus.OK);
        }
    }
}
