package ge.nika.onlinefurnitureshop.controllers;

import ge.nika.onlinefurnitureshop.entities.Product;
import ge.nika.onlinefurnitureshop.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.swing.text.html.CSS.getAttribute;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String cartPage() {
        return "html/cart/cart";
    }

    @PostMapping("/add-product")
    public String addToCart(@RequestParam("id") Integer id, @RequestParam("quantity") int quantity, Model model, HttpSession session) {
        System.out.println("Passed values: \n" + id + "\n" + quantity);

        cartService.addProductToCart(id, quantity, model, session);

        return "html/cart/cart";
    }

    @GetMapping("/remove-product")
    public String removeFromCart(@RequestParam("id") Integer id, HttpSession session, Model model) {

        List<Product> productList = (List<Product>) session.getAttribute("productList");

        List<Product> updatedProducts = new ArrayList<>();
        for (Product p: productList){
            if(!p.getId().equals(id)){
                updatedProducts.add(p);
            }
        }

        session.setAttribute("productList", updatedProducts);
        return "html/cart/cart";
    }


}