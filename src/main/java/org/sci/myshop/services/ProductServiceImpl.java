package org.sci.myshop.services;

import org.sci.myshop.dao.Product;
import org.sci.myshop.repositories.ProductsRepository;
import org.sci.myshop.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public void save(Product product) {
        productsRepository.save(product);
    }

    @Override
    public List<Product> findByName(String name) {
        return productsRepository.findAllByName(name);
    }

    @Override
    public List<Product> findAllProducts() {
        return productsRepository.findAll();
    }

    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productsRepository.search(keyword);
        }
        return productsRepository.findAll();
    }
}
