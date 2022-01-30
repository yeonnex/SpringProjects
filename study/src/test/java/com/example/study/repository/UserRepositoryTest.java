package com.example.study.repository;

import com.example.study.StudyApplication;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.study.StudyApplicationTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)
    @Autowired UserRepository userRepository;

    @Test
    public void create(){
        // String sql = "insert into user (%s, %s, %d) values (account, email, age);
        User user = new User();
        // user.setId(); DB에서 Auto Increment 될 것이기 때문
        user.setAccount("TestUser07");
        user.setPassword("3366");
        user.setStatus("Registered");
        user.setEmail("TestUser09@gmail.com");
        user.setPhoneNumber("010-777-9876");

        User newUser = userRepository.save(user);
        System.out.println("new User: " + newUser);

        User u = User.builder()
                .account("TestUser08")
                .password("123321")
                .status("Registered")
                .email("TestUser09@gmail.com").build();
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-333-3333");
        // 체이닝하여 값을 세팅할 수 있음 @Accessors(chain=true) 덕분!
        user.setEmail("TestUser09@gmail.com").setStatus("Registered").setPhoneNumber("010-9999-0000");
//        assertNotNull(user);
        if (user != null){
            user.getOrderGroupList().forEach(orderGroup -> {
                System.out.println("------------주문 묶음----------------");
                System.out.println("수령인: " + orderGroup.getRevName());
                System.out.println("수령지: " + orderGroup.getRevAddress());
                System.out.println("총금액: " + orderGroup.getTotalPrice());
                System.out.println("총수량: " + orderGroup.getTotalQuantity());
                System.out.println("------------주문 상세----------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름: "+ orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리: "+ orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품: "+ orderDetail.getItem().getName());
                    System.out.println("고객센터번호: "+ orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문 상태: "+ orderDetail.getStatus());
                    System.out.println("도착 예정일자: "+ orderDetail.getArrivalDate());
                });
            });

        }

    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser -> {
            selectUser.setAccount("yeonnex");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("장서연");

            userRepository.save(selectUser);
        });

    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });
        Optional<User> deletedUser = userRepository.findById(1L);
        if(deletedUser.isPresent()){
            System.out.println("삭제되지 않음. 데이터 존재" + deletedUser.get());
        }else{
            System.out.println("삭제됨. 데이터 없음");
        }

    }
}
