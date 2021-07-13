package com.nabers.spring.repositories;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nabers.spring.entities.OrderItem;

@Repository
@Profile(value = {"dev","test","prod"})
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	
	  @Query(nativeQuery = true,value = "select * from ORDER_ITEM A where A.product_id = :id") public
	  Iterable<OrderItem> findbyProduct(@Param(value = "id") Integer id);
	  
	  @Query(nativeQuery = true,value = "select * from ORDER_ITEM A where A.order_id = :id") public
	  Iterable<OrderItem> findbyOrder(@Param(value = "id") Integer id);
	  
	  @Query(nativeQuery = true,
			 value = "select * from ORDER_ITEM A "
			 	   + "where A.product_id = :product_id "
			 	   + "  and A.order_id = :order_id"
	  ) public Optional<OrderItem> findbyId(@Param(value = "product_id") Integer product_id,
	                                        @Param(value = "order_id") Integer order_id);
	  
	  @Query(nativeQuery = true,
				 value = "delete from ORDER_ITEM A "
				 	   + "where A.product_id = :product_id "
				 	   + "  and A.order_id = :order_id")
	  public void deleteById(@Param(value = "product_id") Integer product_id,
                             @Param(value = "order_id") Integer order_id);
	 
	
	

}
