package ge.nika.onlinefurnitureshop.services.impl;

import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.repositories.ProductRepository;
import ge.nika.onlinefurnitureshop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

//    @Override
//    public Page<Product> getSortedProducts(Pageable pageable) {
//        return productRepository.findAll(pageable);
//    }

    @Override
    public Page<Product> getProductsByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable);
    }


//    @Override
//    public Page<Product> getProductsByName(Pageable pageable) {
//        return productRepository.findByName(pageable);
//    }
//
//    @Override
//    public Page<Product> getProductsByPrice(Pageable pageable) {
//        return productRepository.findByPrice(pageable);
//    }
}
