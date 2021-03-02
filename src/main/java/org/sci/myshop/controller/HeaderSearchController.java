package org.sci.myshop.controller;

import org.sci.myshop.dao.Product;
import org.sci.myshop.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import java.util.List;

@Controller
public class HeaderSearchController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/search")
    public String getSearch(Model model){

        List<Product> list = productService.findAllProducts();
        model.addAttribute("listOfProducts", list);
        return "search";
    }
}
