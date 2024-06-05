package ge.nika.onlinefurnitureshop.services;

import ge.nika.onlinefurnitureshop.dtos.ProductDTO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface AdminService {
    public void deleteUser(Integer id);

    public void deleteProduct(Integer id, RedirectAttributes redirectAttributes);

    public void addProduct(ProductDTO productDTO, RedirectAttributes redirectAttributes);
}
