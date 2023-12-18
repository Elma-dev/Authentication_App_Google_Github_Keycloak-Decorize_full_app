package dev.elma.products_ms_secured.entities;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Product {
    @Id
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
}
