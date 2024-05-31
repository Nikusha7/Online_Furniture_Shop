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

//    @GetMapping
//    public String getAllProduct(@RequestParam(name = "page", defaultValue = "0") int pageNumber, Model model) {
//        int pageSize = 12; // Display 12 products per page
//        Pageable pageable = PageRequest.of(pageNumber, pageSize); //displaying 12 products
//        Page<Product> products = productService.getProducts(pageable);
//
//        System.out.println("Page Number: " + pageNumber);
//        products.getContent().forEach(product -> System.out.println("Product: " + product));
//
//        model.addAttribute("products", products.getContent());
//        model.addAttribute("currentPage", pageNumber);
//        model.addAttribute("totalPages", products.getTotalPages());
//
//        return "html/product/all_products";
//    }

    @GetMapping("/category")
    public String getProductsByCategory(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                        @RequestParam(name = "sort", required = false) String sort,
                                        @RequestParam(name = "category", required = false) String category,
                                        Model model) {
        int pageSize = 12;

        System.out.println("/product/category:");
        System.out.println("\npageNumber=" + pageNumber + "\npageSize=" + pageSize + "\nsort=" + sort + "\ncategory=" + category);


        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> products = productService.getProductsByCategory(category, pageable);

        for (Product p : products.getContent()) {
            System.out.println(p.toString());
        }

        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("selectedSort", sort); // Pass selected sort to the view
        model.addAttribute("selectedCategory", category);


        return "html/product/all_products";
    }

    @GetMapping
    public String getProducts(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                              @RequestParam(name = "sort", required = false) String sort,
                              @RequestParam(name = "category", required = false) String category,
                              Model model) {

        Page<Product> products = null;
        int pageSize = 12; // Display 12 products per page
//        Pageable pageable = PageRequest.of(pageNumber, pageSize); // displaying 12 products

        System.out.println("\npageNumber=" + pageNumber + "\npageSize=" + pageSize + "\nsort=" + sort + "\ncategory=" + category);
//        TODO: sorting by category must be done. after sorting by one category for example chair, then filtering and paging should happen on those products

        if (category != null && sort != null && !sort.isEmpty() && !category.isEmpty()) {
            products = productService.getProductsByCategory(category, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "price")));
        } else if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category, PageRequest.of(pageNumber, pageSize));
        } else if (sort != null) {
            Pageable pageable;
            switch (sort) {
                case "by-name-asc":
                    pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "name"));
                    products = productService.getSortedProducts(pageable);
                    break;
                case "by-name-desc":
                    pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "name"));
                    products = productService.getSortedProducts(pageable);
                    break;
                case "price-low-high":
                    pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "price"));
                    products = productService.getSortedProducts(pageable);
                    break;
                case "price-high-low":
                    pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "price"));
                    products = productService.getSortedProducts(pageable);
                    break;
                default:
                    pageable = PageRequest.of(pageNumber, pageSize);
                    products = productService.getProducts(pageable);
                    break;
            }
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            // If sort is not selected, fetch products with default sorting
            products = productService.getProducts(pageable);
        }

        List<Product> productList = products.getContent();
        for (Product p : productList) {
            System.out.println(p.toString());
        }


        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("selectedSort", sort); // Pass selected sort to the view
        model.addAttribute("selectedCategory", category);

        return "html/product/all_products";
    }


//    @GetMapping("/sort")
//    public String getAllProductByName(@RequestParam(name = "sort") String sort,
//                                      @RequestParam(name = "page", defaultValue = "0") int pageNumber,
//                                      Model model) {
//        int pageSize = 12; // Display 12 products per page
//        Page<Product> products;
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "name"));
//        System.out.println("\npageNumber=" + pageNumber + "\npageSize=" + pageSize + "\nsort=" + sort);
//
//        switch (sort) {
//            case "by-name-asc":
//                products = productService.getProductsByNameAsc(pageable);
//                break;
//            case "by-name-desc":
//
//                products = productService.getProductsByNameAsc(pageable);
//                break;
//            case "price-low-high":
//                products = productService.getProductsByNameAsc(pageable);
//                break;
//            case "price-high-low":
//                products = productService.getProductsByNameAsc(pageable);
//                break;
//            default:
//                products = productService.getProducts(pageable);
//                break;
//        }
//
//
//        System.out.println("Page Number: " + pageNumber);
//        products.forEach(product -> System.out.println("Product: " + product));
//
//        model.addAttribute("products", products);
//        model.addAttribute("currentPage", pageNumber);
//        model.addAttribute("totalPages", products.getTotalPages());
//        model.addAttribute("selectedSort", sort);
//
//        return "html/product/all_products";
//    }

    @GetMapping("/single_product")
    public String getAllProduct(@RequestParam("id") Integer id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            System.out.println("Product with ID: " + id + " does not exists!!!!!!!!");
        }
        return "html/product/single_product";
    }
}