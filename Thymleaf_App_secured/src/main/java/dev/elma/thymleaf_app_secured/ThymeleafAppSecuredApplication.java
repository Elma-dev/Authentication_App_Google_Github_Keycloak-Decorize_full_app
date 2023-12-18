package dev.elma.thymleaf_app_secured;

import dev.elma.thymleaf_app_secured.entities.Customer;
import dev.elma.thymleaf_app_secured.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication @AllArgsConstructor
public class ThymeleafAppSecuredApplication implements CommandLineRunner {
	private CustomerRepository customerRepository;
	public static void main(String[] args) {
		SpringApplication.run(ThymeleafAppSecuredApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List.of("Ahmed","Mohammed","Yacine","Younes").forEach(
				x->{
					customerRepository.save(Customer.builder().name(x).email(x+"@email.com").build());
				}
		);
	}
}
