package com.longma.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longma.cursomc.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
