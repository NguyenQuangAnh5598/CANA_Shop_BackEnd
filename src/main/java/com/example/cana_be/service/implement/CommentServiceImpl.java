package com.example.cana_be.service.implement;

import com.example.cana_be.model.Commentt;
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
    public List<Commentt> findAll() {
        return null;
    }

    @Override
    public Optional<Commentt> findById(Long id) {
        return iCommentRepo.findById(id);
    }

    @Override
    public void save(Commentt commentt) {
        iCommentRepo.save(commentt);
    }

    @Override
    public void remove(Long id) {
        iCommentRepo.deleteById(id);
    }

    @Override
    public List<Commentt> findAllByProductId(Long productId) {
        return iCommentRepo.findAllByProductIdOrderById(productId);
    }

}
