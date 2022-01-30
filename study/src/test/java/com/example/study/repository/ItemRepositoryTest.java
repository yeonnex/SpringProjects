package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    // 간단하게, create 와 read 에 대해서만 해보자
    @Test
    public void create(){
        Item item = new Item();
        item.setStatus("REGISTERED");
        item.setName("Notebook");
        item.setTitle("노트북");
        item.setPrice(100000);
        item.setContent("Made in LG");

        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        item.setRegisteredAt(registeredAt);
        item.setCreatedAt(createdAt);
        item.setCreatedBy(createdBy);
//        item.setPartnerId(1L);
        Item newItem = itemRepository.save(item);
        System.out.println(newItem);
    }

    @Test
    public void read(){
        Long id = 3L;
        Optional<Item> item = itemRepository.findById(id);
        item.ifPresent(selectItem -> {
//            System.out.println(selectItem.getPartnerId());
        });
    }

}
