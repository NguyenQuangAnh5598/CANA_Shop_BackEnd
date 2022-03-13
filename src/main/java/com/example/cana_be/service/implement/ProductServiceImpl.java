package com.example.cana_be.service.implement;

import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.model.Product;
import com.example.cana_be.repository.IProductRepo;
import com.example.cana_be.service.extend.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepo productRepo;

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepo.deleteById(id);
    }


    @Override
    public void setQuantity(Product product, int quantity) {
        product.setQuantity(product.getQuantity() - quantity);
        productRepo.save(product);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepo.findByName(name);
    }

}
