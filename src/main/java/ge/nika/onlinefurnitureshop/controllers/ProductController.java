package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                              @RequestParam(name = "sort", required = false) String sort,
                              @RequestParam(name = "category", required = false) String category,
                              Model model) {
        int pageSize = 12; // Display 12 products per page
        System.out.println("\npageNumber=" + pageNumber + "\npageSize=" + pageSize + "\nsort=" + sort + "\ncategory=" + category);

        productService.getProducts(category, pageNumber, pageSize, sort, model);
        return "html/product/all_products";
    }


    @GetMapping("/single_product")
    public String getSingleProduct(@RequestParam("id") Integer id, Model model) {
        productService.getSingleProduct(id, model);
        return "html/product/single_product";
    }
}