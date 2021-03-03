package org.sci.myshop.controller;

import org.sci.myshop.dao.Product;
import org.sci.myshop.repositories.ProductsRepository;
import org.sci.myshop.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/SearchResults")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Product> listProducts = productService.listAll(keyword);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", keyword);

        return "SearchResults";
    }

    @RequestMapping("/CarParts")
    public String getCarParts(Model model){
        List<Product> listProducts = productService.listAll("Car Parts");
        model.addAttribute("listProducts", listProducts);
        return "CarParts";
    }

    @RequestMapping("/Batteries")
        public String getBatteries(Model model){
        List<Product> listProducts = productService.listAll("Batteries");
        model.addAttribute("listProducts", listProducts);
        return "Batteries";
    }

    @RequestMapping("/Oil")
        public String getOil(Model model){
        List<Product> listProducts = productService.listAll("Oil");
        model.addAttribute("listProducts", listProducts);
        return "Oil";
    }

    @RequestMapping("/Tires")
        public String getTires(Model model){
        List<Product> listProducts = productService.listAll("Tires");
        model.addAttribute("listProducts", listProducts);
        return "Tires";
    }

    @RequestMapping("/Sensors")
        public String getSensors(Model model){
        List<Product> listProducts = productService.listAll("Sensors");
        model.addAttribute("listProducts", listProducts);
        return "Sensors";
    }

    @RequestMapping("/Tools")
    public String getTools(Model model){
        List<Product> listProducts = productService.listAll("Tools");
        model.addAttribute("listProducts", listProducts);
        return "Tools";
    }
}
