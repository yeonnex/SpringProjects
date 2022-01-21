package com.example.filter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class FilterApplicationTests {

    @Test
    void contextLoads() {

        List<String> title = Arrays.asList("kim", "seo", "hae");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
    }

}
