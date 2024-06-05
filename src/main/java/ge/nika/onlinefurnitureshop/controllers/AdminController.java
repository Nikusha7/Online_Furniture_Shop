package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.dtos.ProductDTO;
import ge.nika.onlinefurnitureshop.services.AdminService;
import ge.nika.onlinefurnitureshop.services.ProductService;
import ge.nika.onlinefurnitureshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final ProductService productService;
    private final AdminService adminService;

    public AdminController(UserService userService, ProductService productService, AdminService adminService) {
        this.userService = userService;
        this.productService = productService;
        this.adminService = adminService;
    }


    @GetMapping
    public String adminPage(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                            @RequestParam(name = "sort", required = false) String sort,
                            @RequestParam(name = "category", required = false) String category,
                            Model model) {
        System.out.println("Admin panel was called");
        model.addAttribute("productDTO", new ProductDTO());

        userService.getAllUsers(model);
        productService.getProducts(category, pageNumber, Integer.MAX_VALUE, sort, model);

        return "html/admin/admin_panel";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam(name = "userId") Integer id, RedirectAttributes redirectAttributes) {
        System.out.println("Admin attempts to delete user with id = " + id);

        // Delete user roles associated with the user
        userService.deleteUserById(id);
        // Then delete the user
        adminService.deleteUser(id);

        redirectAttributes.addFlashAttribute("userMessage", "User deleted successfully.");
        return "redirect:/admin";
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam(name = "productId") Integer id, RedirectAttributes redirectAttributes) {
        System.out.println("Admin attempts to delete product with id = " + id);

        adminService.deleteProduct(id, redirectAttributes);

        return "redirect:/admin";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO, RedirectAttributes redirectAttributes){
        System.out.println("Admin tries to add product: "+productDTO.toString());

        adminService.addProduct(productDTO, redirectAttributes);

        return "redirect:/admin";
    }

}