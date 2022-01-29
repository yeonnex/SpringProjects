package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.assertj.core.api.Assert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryRepositoryTest extends StudyApplicationTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create(){
        String type = "refrigerator";
        String title = "냉장고";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);
        assertNotNull(newCategory);
        assertEquals(newCategory.getType(), type);
        assertEquals(newCategory.getTitle(), title);
    }

    @Test
    public void read(){
        Optional<Category> optionalCategory = categoryRepository.findById(1L);

        optionalCategory.ifPresent(c -> {
            System.out.println(c.getId());
            System.out.println(c.getTitle());
            System.out.println(c.getType());
        });

        // select * from category where type = "COMPUTER" 를 하기 위해 쿼리메소드!
        Optional<Category> categoryType = categoryRepository.findByType("컴퓨터");
        categoryType.ifPresent(c -> {
            System.out.println(c.getId());
            System.out.println(c.getTitle());
            System.out.println(c.getType());
        });

    }

}
