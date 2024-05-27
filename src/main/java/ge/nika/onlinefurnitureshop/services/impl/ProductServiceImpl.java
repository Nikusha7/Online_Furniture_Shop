package ge.nika.onlinefurnitureshop.services.impl;

import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.repositories.ProductRepository;
import ge.nika.onlinefurnitureshop.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
