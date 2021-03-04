package org.sci.myshop.repositories;

import org.sci.myshop.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    public List<Product> findAllByName(String name);

    @Query("SELECT p FROM Product p WHERE UPPER(CONCAT(p.name, ' ', p.category, ' ', p.description, ' ', p.manufacturer, ' ', p.price)) LIKE UPPER(CONCAT('%', ?1, '%')) ")
    public List<Product> search(String keyword);
}
