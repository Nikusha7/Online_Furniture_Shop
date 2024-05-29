package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProduct(@RequestParam(name = "page", defaultValue = "0") int pageNumber, Model model) {
        int pageSize = 12; // Display 12 products per page
        Pageable pageable = PageRequest.of(pageNumber, pageSize); //displaying 12 products
        Page<Product> products = productService.getProducts(pageable);

        System.out.println("Page Number: " + pageNumber);
        products.getContent().forEach(product -> System.out.println("Product: " + product));

        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", products.getTotalPages());

        return "html/product/all_products";
    }

    @GetMapping("/single_product")
    public String getAllProduct(@RequestParam("id") Integer id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if(product.isPresent()) {
            model.addAttribute("product", product.get());
        }else{
            System.out.println("Product with ID: "+id+" does not exists!!!!!!!!");
        }
        return "html/product/single_product";
    }
}