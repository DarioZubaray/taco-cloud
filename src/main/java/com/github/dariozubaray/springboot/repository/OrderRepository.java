package com.github.dariozubaray.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.github.dariozubaray.springboot.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
