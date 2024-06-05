package ge.nika.onlinefurnitureshop.services.impl;

import ge.nika.onlinefurnitureshop.dtos.ProductDTO;
import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.repositories.ProductRepository;
import ge.nika.onlinefurnitureshop.repositories.UserRepository;
import ge.nika.onlinefurnitureshop.services.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public AdminServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteProduct(Integer id, RedirectAttributes redirectAttributes) {
        productRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("productMessage", "Product deleted successfully!");
    }

    @Override
    public void addProduct(ProductDTO productDTO, RedirectAttributes redirectAttributes) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .imageUrl(productDTO.getImageUrl())
                .category(productDTO.getCategory())
                .build();

        productRepository.save(product);
        redirectAttributes.addFlashAttribute("productMessage", "Product added successfully.");
    }

}