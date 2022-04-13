package com.example.cana_be.service.extend;

import com.example.cana_be.model.Commentt;
import com.example.cana_be.service.IGeneralService;

import java.util.List;

public interface ICommentService extends IGeneralService<Commentt> {
    List<Commentt> findAllByProductId(Long productId);
}
