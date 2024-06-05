package ge.nika.onlinefurnitureshop.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String category;

    // Constructors, getters, and setters
}