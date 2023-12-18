package dev.elma.thymleaf_app_secured.repositories;

import dev.elma.thymleaf_app_secured.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
