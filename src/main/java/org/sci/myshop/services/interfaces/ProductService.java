package org.sci.myshop.services.interfaces;

import org.sci.myshop.dao.Product;

import java.util.List;

public interface ProductService {

    void save(Product product);

    public List<Product> findByName(String name);


    public List<Product> findAllProducts();


}
