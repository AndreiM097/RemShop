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
        for (int i = 0; i<carPartsManufacturers.length; i++) {
            Product product = new Product();
            product.setName(carPartsProductNames[i]);
            product.setCategory("Car Parts");
            product.setPictureLocation(i + ".jpg");
            product.setDescription("Descriere exemplu");
            product.setManufacturer(carPartsManufacturers[i]);
            product.setPrice(20.55 + i);

            productService.save(product);
        }

            String[] batteriesProductNames = {"Baterie auto Bosch","Baterie auto Rombat","Baterie auto Duracell", "Baterie auto Tudor", "Baterie auto Varta", "Baterie auto Monbat", "Baterie auto Exide"};
            String[] batteriesImageLocations = {"piston.png", "leftShaft.jpg", "rightShaft.jpg", "egr.jpg", "turbocharger.jpg", "camshaft.jpg"};
            String[] batteriesProductManufacturers = {"Bosch","Rombat","Duracell", "Tudor", "Varta", "Monbat", "Exide"};

            for (int j = 0; j<batteriesProductNames.length; j++){
                Product battery = new Product();
                battery.setName(batteriesProductNames[j]);
                battery.setCategory("Batteries");
                battery.setPictureLocation(j + ".jpg");
                battery.setDescription("Descriere exemplu");
                battery.setManufacturer(batteriesProductManufacturers[j]);
                battery.setPrice(20.55 + j);

                productService.save(battery);
        }

        String[] oilProductNames = {"Turbo Diesel 5W40","SYNT RSI 5W40","Tec 4100 5W40", "LONG LIFE 5W30", "Ecology 5W30", "700 STI 10W-40", "SuperDiesel 10w40"};
        String[] oilProductManufacturers = {"Castrol","Rowe","Liqui Moly", "Repsol", "Kennol", "Elf", "Hexol"};

        for (int k = 0; k<oilProductNames.length; k++){
            Product oil = new Product();
            oil.setName(oilProductNames[k]);
            oil.setCategory("Oil");
            oil.setPictureLocation(k + ".jpg");
            oil.setDescription("Descriere exemplu");
            oil.setManufacturer(oilProductManufacturers[k]);
            oil.setPrice(20.55 + k);

            productService.save(oil);
        }

        String[] tiresProductNames = {"ECOCONTACT 225/45R17","CrossClimate 205/5 R16","Cinturato 205/55 R16", "Ventus Prime 205/55 R16", "T005 205/55 R16", "91H 195/65 R16", "Wetproof 195/65 R16"};
        String[] tiresProductManufacturers = {"Continental","Michelin","Pireli", "Hankook", "Bridgestone", "Goodyear", "NOKIAN"};

        for (int l = 0; l<tiresProductNames.length; l++){
            Product tires = new Product();
            tires.setName(tiresProductNames[l]);
            tires.setCategory("Tires");
            tires.setPictureLocation(l + ".jpg");
            tires.setDescription("Descriere exemplu");
            tires.setManufacturer(tiresProductManufacturers[l]);
            tires.setPrice(20.55 + l);

            productService.save(tires);
        }

        String[] toolsProductNames = {"Surubelnita YT-28015","Cric auto hidraulic","Ventuza pentru sticla D-117", "Canistra pentru ulei 8L", "Trusa profesionala auto", "Separator etrier frana cu clichet", "Cheie schimb filtru ulei cu lant"};
        String[] toolsImageLocations = {"piston.png", "leftShaft.jpg", "rightShaft.jpg", "egr.jpg", "turbocharger.jpg", "camshaft.jpg"};
        String[] toolsProductManufacturers = {"Yato","Petex","Sparta", "Carmax", "LTI", "NEO Tools", "RUNKIT"};

        for (int m = 0; m<toolsProductNames.length; m++){
            Product tools = new Product();
            tools.setName(toolsProductNames[m]);
            tools.setCategory("Tools");
            tools.setPictureLocation(m + ".jpg");
            tools.setDescription("Descriere exemplu");
            tools.setManufacturer(toolsProductManufacturers[m]);
            tools.setPrice(20.55 + m);

            productService.save(tools);
        }

        String[] sensorsProductNames = {"Lambda sensor","Map Sensor","Fuel temperature", "Tire pressure monitoring kit", "DPF Vaccum boost sensor", "Pan sensor", "Fuel Rail Sensor"};
        String[] sensorsImageLocations = {"0", "1", "2", "3", "4", "5", "6"};

        for (int n = 0; n<sensorsProductNames.length; n++){
            Product sensors = new Product();
            sensors.setName(sensorsProductNames[n]);
            sensors.setCategory("Sensors");
            sensors.setPictureLocation(sensorsImageLocations[n] + ".jpg");
            sensors.setDescription("Descriere exemplu");
            sensors.setManufacturer("Sensors");
            sensors.setPrice(20.55 + n);

            productService.save(sensors);
        }
    }
}