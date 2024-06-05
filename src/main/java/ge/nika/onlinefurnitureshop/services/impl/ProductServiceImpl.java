package ge.nika.onlinefurnitureshop.services.impl;

import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.repositories.ProductRepository;
import ge.nika.onlinefurnitureshop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void getSingleProduct(Integer id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        } else {
            System.out.println("Product with ID: " + id + " does not exists!!!!!!!!");
        }
    }

    @Override
    public void getProducts(String category, int pageNumber, int pageSize, String sort, Model model) {
        Page<Product> products;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (sort != null && !sort.isEmpty()) {
            switch (sort) {
                case "by-name-asc":
                    pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "name"));
                    break;
                case "by-name-desc":
                    pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "name"));
                    break;
                case "price-low-high":
                    pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "price"));
                    break;
                case "price-high-low":
                    pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "price"));
                    break;
                default:
                    pageable = PageRequest.of(pageNumber, pageSize);
                    break;
            }
        }

        if (category != null && !category.isEmpty()) {
            products = productRepository.findByCategory(category, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        model.addAttribute("products", products.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("selectedSort", sort); // Pass selected sort to the view
        model.addAttribute("selectedCategory", category);
    }


}