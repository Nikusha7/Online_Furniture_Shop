package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.entities.MyUser;
import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.services.ProductService;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final ProductService productService;
    public AdminController(UserService userService, ProductService productService){
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public String adminPage(Model model) {
        System.out.println("admin panel was invoked");

        List<MyUser> users = userService.getAllUsers();
        List<Product> products = productService.getAllProducts();

        model.addAttribute("users", users);
        model.addAttribute("products", products);
        return "html/admin/admin_panel";
    }


}