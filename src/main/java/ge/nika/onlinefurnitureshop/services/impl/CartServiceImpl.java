package ge.nika.onlinefurnitureshop.services.impl;

import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.repositories.ProductRepository;
import ge.nika.onlinefurnitureshop.services.CartService;
import ge.nika.onlinefurnitureshop.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;

    public CartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProductToCart(Integer productId, int quantity, Model model, HttpSession session) {
        Optional<Product> product = productRepository.findById(productId);

        List<Product> productList = new ArrayList<>();
        if (product.isPresent()) {
            for (int i = 0; i < quantity; i++) {
                productList.add(product.get());
            }
        }
        System.out.println("Product: "+product.get());
        session.setAttribute("productList", productList);
        model.addAttribute("productList", productList);
    }
}
