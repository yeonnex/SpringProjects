package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.test.context.TestExecutionListeners;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus("WAITING");
        orderDetail.setQuantity(100);
        orderDetail.setTotalPrice(BigDecimal.valueOf(124000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("ADMIN");
        orderDetail.setItemId(1L);
//        orderDetail.setOrderGroupId(1L);
    }

}
