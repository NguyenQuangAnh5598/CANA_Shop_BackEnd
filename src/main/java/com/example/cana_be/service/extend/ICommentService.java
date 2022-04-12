package com.example.cana_be.service.extend;

import com.example.cana_be.model.Comment;
import com.example.cana_be.service.IGeneralService;

import java.util.List;

public interface ICommentService extends IGeneralService<Comment> {
    List<Comment> findAllByProductId(Long productId);
}
