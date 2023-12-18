package dev.elma.products_ms_secured;

import dev.elma.products_ms_secured.entities.Product;
import dev.elma.products_ms_secured.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@AllArgsConstructor
public class ProductsMsSecuredApplication implements CommandLineRunner {
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProductsMsSecuredApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List.of("Apple","Samsung","LG").forEach(
				gadget->{
					productRepository.save(Product.builder()
							.id(UUID.randomUUID().toString())
							.name(gadget)
							.quantity(gadget.length()*10)
							.price(gadget.length()*100.1D)
							.build());
				}
		);

	}
}
