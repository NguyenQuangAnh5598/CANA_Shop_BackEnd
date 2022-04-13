package com.example.cana_be.controller;

import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.Commentt;
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
    public ResponseEntity<?> createNewComment(@RequestBody Commentt commentt) {
        commentService.save(commentt);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/showAllComment")
    public ResponseEntity<?> findAllCommentByProductId(@RequestBody String id) {
        Long newId =  Long.parseLong(id);
        List<Commentt> commenttList = commentService.findAllByProductId(newId);
        return new ResponseEntity<>(commenttList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findComment(@PathVariable Long id) {
        List<Commentt> commenttList = commentService.findAllByProductId(id);
        return new ResponseEntity<>(commenttList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Commentt> commentOptional = commentService.findById(id);
        User user = usersDetailService.getCurrentUser();
        if(!commentOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("NOT"), HttpStatus.OK);
        } else if(commentOptional.get().getUser().getId() == user.getId()) {
            commentService.remove(id);
            return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("NO"), HttpStatus.OK);
        }
    }
}
