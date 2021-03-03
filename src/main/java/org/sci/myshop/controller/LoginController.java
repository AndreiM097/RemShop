package org.sci.myshop.controller;

import org.sci.myshop.dao.Product;
import org.sci.myshop.dao.Role;
import org.sci.myshop.dao.User;
import org.sci.myshop.services.ProductServiceImpl;
import org.sci.myshop.services.interfaces.RolesService;
import org.sci.myshop.services.interfaces.SecurityService;
import org.sci.myshop.services.interfaces.UserService;
import org.sci.myshop.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

            return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        if (servletContext.getAttribute("init")==null) {
            initUsersData();
            initProductsData();
            servletContext.setAttribute("init", true);
        }
            return "welcome";
        }

    @GetMapping("/userList")
    public String getUserList(Model model){
        List<User> list = userService.findAllUsers();
        model.addAttribute("listOfUsers",list);

        return "userList";
    }

    private void initUsersData(){
        List<Role> roles = new ArrayList<>();
        Role adminRole =new Role();
        Role userRole =new Role();
        adminRole.setName("ADMIN");
        userRole.setName("USER");
        roles.add(adminRole);
        roles.add(userRole);
        rolesService.saveRoles(roles);
        roles=rolesService.findAllRoles();
        User adminUser = new User();
        adminUser.setUsername("rem");
        adminUser.setPassword("password");
        adminUser.setFullName("remUser");
        adminUser.setAddress("RemAddress");

        adminUser.setRole(roles.get(1));

        userService.save(adminUser);

        String[] adminUsers = {"Rares", "Marian", "Marius", "Robert"};
        for (int i = 0;i<adminUsers.length;i++){
            User admins = new User();
            admins.setUsername(adminUsers[i]);
            admins.setPassword("password");
            admins.setFullName(adminUsers[i]);
            admins.setAddress(adminUsers[i] + "'s " + "address");
            admins.setRole(roles.get(0));
            userService.save(admins);
        }

        for (int i = 1;i <= 5;i++){
            User fakeUser = new User();
            fakeUser.setUsername("User" + i);
            fakeUser.setPassword("password");
            fakeUser.setFullName("Fake User " + i);
            fakeUser.setAddress("Fake Adress " + i);
            fakeUser.setRole(roles.get(1));
            userService.save(fakeUser);
        }
    }

    private void initProductsData(){

        String[] carPartsProductNames = {"Piston","Planetara stanga","Planetara dreapta", "EGR", "Turbina", "Ax cu came", "Vibrochen"};
        String[] productImageLocations = {"piston.png", "leftShaft.jpg", "rightShaft.jpg", "egr.jpg", "turbocharger.jpg", "camshaft.jpg"};
        String[] carPartsManufacturers = {"BMW", "Audi", "Volkswagen", "Volkswagen", "Dacia", "Opel"};
        for (int i = 0; i<carPartsManufacturers.length; i++){
            Product product = new Product();
            product.setName(carPartsProductNames[i]);
            product.setCategory("Car Parts");
            product.setPictureLocation(productImageLocations[i]);
            product.setDescription("Descriere exemplu");
            product.setManufacturer(carPartsManufacturers[i]);
            product.setPrice(20.55 + i);

            productService.save(product);
        }
    }
}
