package com.nabers.spring.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nabers.spring.entities.Order;

@Repository
@Profile(value = {"dev","test","prod"})
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
