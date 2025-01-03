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
            " WHERE EXTRACT(YEAR FROM order_date) = :year"+
            " AND order_status = 'Completed'"+
            " GROUP BY restaurant_id, EXTRACT(MONTH FROM order_date)"+
            " ORDER BY restaurant_id, month", nativeQuery = true)
    List<Object[]> findRestaurantOrdersByYear(Long year);

    @Query(value = "SELECT \n" +
            "    rl.restaurant_name,\n" +
            "    rl.restaurant_id,"+
            "    (EXTRACT(YEAR FROM AGE(MAX(o.order_date), MIN(o.order_date))) * 12 + \n" +
            "    EXTRACT(MONTH FROM AGE(MAX(o.order_date), MIN(o.order_date))) + 1) AS totalMonths,\n" +
            "    COUNT(*) AS totalOrderCount,\n" +
            "    rl.restaurant_type\n" +
            "FROM \n" +
            "    orders o\n" +
            "JOIN \n" +
            "    restaurant_lead rl\n" +
            "ON \n" +
            "    o.restaurant_id = rl.restaurant_id\n" +
            "GROUP BY \n" +
            "    rl.restaurant_name,\n" +
            "    rl.restaurant_type,"+
            "    rl.restaurant_id;",nativeQuery = true)
    List<Object[]> findAverageOrdersPerMonth();
}
