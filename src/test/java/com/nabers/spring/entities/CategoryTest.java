package com.nabers.spring.entities;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category = new Category();

    @Test
    void getId() {
        int id = 45;
        category.setId(id);
        assertEquals(id, category.getId());
    }

    @Test
    void setId() {
        category.setId(13);
        assertEquals(category.getId(), 13);
    }

    @Test
    void getName() {
        String testName = "test";
        category.setName(testName);
        assertEquals(category.getName(), testName);
    }

    @Test
    void setName() {
        category.setName("teste");
        assertEquals(category.getName(), "teste");
    }

    @Test
    void getProducts() {
        category.getProducts().add(new Product(1, "teste", "teste", 0.00));
        int size = category.getProducts().size();
        Product prod = category.getProducts()
                .stream()
                .filter(x -> x.equals(new Product(1, "teste", "teste", 0.00))).findAny().get();
        if (size == 1 && prod != null) {
        } else {
            throw new RuntimeException();
        }
    }
}