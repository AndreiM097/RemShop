package org.sci.myshop.controller;

import org.sci.myshop.dao.User;
import org.sci.myshop.services.UserServiceImpl;
import org.sci.myshop.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserListController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/userList")
    public String getUserList(Model model){
        List<User> list = userService.findAllUsers();
        model.addAttribute("listOfUsers",list);

        return "userList";
    }

    @PostMapping("/userList/{id}")
    public String deleteProductById(@PathVariable Long id, Model model){
       userService.deleteUser(id);
       return "redirect:/userList";
    }
}
