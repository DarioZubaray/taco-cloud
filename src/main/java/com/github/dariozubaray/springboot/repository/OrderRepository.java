package com.github.dariozubaray.springboot.repository;

import com.github.dariozubaray.springboot.models.Order;

public interface OrderRepository {

    Order save(Order order);
}
