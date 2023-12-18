package dev.elma.products_ms_secured.repositories;


import dev.elma.products_ms_secured.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
