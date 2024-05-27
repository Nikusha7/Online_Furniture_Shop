package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.services.ProductService;
import ge.nika.onlinefurnitureshop.services.impl.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProduct(Model model) {
        List<Product> products = productService.getAllProducts();

        model.addAttribute("products", products);

        return "html/product/all_products";
    }
}