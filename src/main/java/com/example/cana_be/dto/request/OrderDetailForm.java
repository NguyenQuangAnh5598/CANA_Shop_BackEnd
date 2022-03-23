package com.example.cana_be.dto.request;

import com.example.cana_be.model.Orders;
import com.example.cana_be.model.Product;

public class OrderDetailForm {
    private Long productId;

    private int orderQuantity;

    private Long ordersId;

    public OrderDetailForm() {
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }
}
