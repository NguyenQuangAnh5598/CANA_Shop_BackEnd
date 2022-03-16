package com.example.cana_be.service.extend;

import com.example.cana_be.model.Category;
import com.example.cana_be.service.IGeneralService;

import java.util.List;

public interface ICategoryService extends IGeneralService<Category> {
    public List<Category> findCategoryByName(String name);
}
