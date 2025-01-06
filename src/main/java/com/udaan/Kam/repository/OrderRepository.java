package com.udaan.Kam.repository;

import com.udaan.Kam.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

    @Query(value = "SELECT restaurant_id AS restaurantId,"+
            " EXTRACT(MONTH FROM order_date) AS month,"+
            " COUNT(*) AS totalOrders,"+
            " AVG(order_amount) AS averageOrderAmount"+
            " FROM orders"+
            " WHERE EXTRACT(YEAR FROM order_date) = :year AND restaurant_id = :restaurantId "+
            " AND order_status = 'Completed'"+
            " GROUP BY restaurant_id, EXTRACT(MONTH FROM order_date)"+
            " ORDER BY restaurant_id, month", nativeQuery = true)
    List<Object[]> findRestaurantOrdersByYear(Long year,Long restaurantId);

    @Query(value = "SELECT " +
    "rl.restaurant_name, "+
    "rl.restaurant_id, " +
            "((DATE_PART('year', MAX(o.order_date)) - DATE_PART('year', MIN(o.order_date))) * 12 + " +
           " (DATE_PART('month', MAX(o.order_date)) - DATE_PART('month', MIN(o.order_date)))) + 1 AS totalMonths, " +
   " COUNT(*) AS totalOrderCount, " +
    "rl.restaurant_type FROM " +
   " orders o "+
    " JOIN restaurant_lead rl "+
    "ON o.restaurant_id = rl.restaurant_id "+
    "GROUP BY "+
   " rl.restaurant_name, rl.restaurant_type, rl.restaurant_id;",
    nativeQuery = true)
    List<Object[]> findAverageOrdersPerMonth();
}
