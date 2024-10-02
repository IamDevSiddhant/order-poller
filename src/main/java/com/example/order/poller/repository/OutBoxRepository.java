package com.example.order.poller.repository;

import com.example.order.poller.entity.OrderOutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OutBoxRepository extends JpaRepository<OrderOutBox,Long> {
    @Query(value = "select * from outbox where processed=0",nativeQuery = true)
    List<OrderOutBox>findByProcessedFalse();
}
