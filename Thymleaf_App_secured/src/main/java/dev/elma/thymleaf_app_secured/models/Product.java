package dev.elma.thymleaf_app_secured.models;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Product {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
}
