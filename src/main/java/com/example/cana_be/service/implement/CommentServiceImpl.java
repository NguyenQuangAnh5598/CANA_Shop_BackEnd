package com.example.cana_be.service.implement;

import com.example.cana_be.model.Comment;
import com.example.cana_be.repository.ICommentRepo;
import com.example.cana_be.security.userprincal.UsersDetailService;
import com.example.cana_be.service.extend.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentRepo iCommentRepo;

    @Autowired
    UsersDetailService usersDetailService;

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return iCommentRepo.findById(id);
    }

    @Override
    public void save(Comment comment) {
        iCommentRepo.save(comment);
    }

    @Override
    public void remove(Long id) {
        iCommentRepo.deleteById(id);
    }

    @Override
    public List<Comment> findAllByProductId(Long productId) {
        return iCommentRepo.findAllByProductId(productId);
    }

}
