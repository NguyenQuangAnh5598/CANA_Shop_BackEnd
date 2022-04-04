package com.example.cana_be.service.extend;

import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.model.Product;
import com.example.cana_be.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    void setQuantity(Product product, int quantity);
    public List<Product> findProductByCategoryName(String name);
    List<Product> findProductByPrice(double minPrice, double maxPrice);
    List<Product> findByName(String name);

    List<Product> top3BestSale();


Page<Product> findAllProduct(Pageable pageable);

}
