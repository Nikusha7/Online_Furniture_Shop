package ge.nika.onlinefurnitureshop.repositories;

import ge.nika.onlinefurnitureshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Integer> {
}