package ge.nika.onlinefurnitureshop.services;

import ge.nika.onlinefurnitureshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Optional<Product> getProductById(Integer id);

    public Page<Product> getProducts(Pageable pageable);

//    public Page<Product> getProductsByName(Pageable pageable);
//
//    public Page<Product> getProductsByPrice(Pageable pageable);
//    public Page<Product> getSortedProducts(Pageable pageable);
    public Page<Product> getProductsByCategory(String category, Pageable pageable);
}
