package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.test.context.TestExecutionListeners;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderAt(LocalDateTime.now());

        // 어떤 사람?
        // orderDetail.setUserId(2L);

        // 어떤 상품?
        // orderDetail.setItemId(1L);

        // 저장!
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        System.out.println(newOrderDetail);
    }

}
