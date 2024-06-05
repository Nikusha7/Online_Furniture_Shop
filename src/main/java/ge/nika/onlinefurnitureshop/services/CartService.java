package ge.nika.onlinefurnitureshop.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public interface CartService {
    public void addProductToCart(Integer productId, int quantity, Model model, HttpSession session);

}
