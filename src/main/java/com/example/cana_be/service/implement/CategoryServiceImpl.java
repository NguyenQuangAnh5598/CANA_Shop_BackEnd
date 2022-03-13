package com.example.cana_be.service.implement;

import com.example.cana_be.model.Category;
import com.example.cana_be.repository.ICategoryRepo;
import com.example.cana_be.service.extend.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryRepo styleRepo;

    @Override
    public List<Category> findAll() {
        return styleRepo.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return styleRepo.findById(id);
    }

    @Override
    public void save(Category category) {
        styleRepo.save(category);
    }

    @Override
    public void remove(Long id) {
        styleRepo.deleteById(id);
    }

    @Override
    public List<Category> findCategoryByName(String name) {
        return styleRepo.findCategoryByName(name);
    }
}
