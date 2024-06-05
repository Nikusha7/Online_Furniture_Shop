package ge.nika.onlinefurnitureshop.services;

import ge.nika.onlinefurnitureshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public void getSingleProduct(Integer id, Model model);
    public void getProducts(String category, int pageNumber, int pageSize, String sort, Model model);
}
