package com.example.cana_be.service.extend;

import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.model.Product;
import com.example.cana_be.service.IGeneralService;

public interface IProductService extends IGeneralService<Product> {
void setQuantity(Product product, int quantity);
}