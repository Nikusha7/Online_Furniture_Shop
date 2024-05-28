package ge.nika.onlinefurnitureshop.services;

import ge.nika.onlinefurnitureshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();
    public Optional<Product> getProductById(Integer id);
    public Page<Product> getProducts(Pageable pageable);
}
