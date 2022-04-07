package com.example.cana_be.service.extend;

import com.example.cana_be.dto.request.OrderDetailForm;
import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.model.Orders;
import com.example.cana_be.service.IGeneralService;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    Iterable<OrderDetail> findAllByOrders(Orders orders);
    OrderDetail createNewOrderDetail(OrderDetailForm orderDetailForm);
}
