package org.gdas.bigreportsapi;

import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.model.enummeration.Measure;
import org.gdas.bigreportsapi.repository.ProductsComponentsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ShopAdminAPIApplication implements CommandLineRunner {

	private final ProductsComponentsRepository repository;

	public ShopAdminAPIApplication(ProductsComponentsRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopAdminAPIApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.saveAll(
				List.of(
						new ProductComponent(LocalDateTime.now(), "Papel A4", Measure.UNITY),
						new ProductComponent(LocalDateTime.now(), "Papel A5", Measure.UNITY),
						new ProductComponent(LocalDateTime.now(), "Papel Fotográfico", Measure.UNITY),
						new ProductComponent(LocalDateTime.now(), "Papel Holler", Measure.UNITY),
						new ProductComponent(LocalDateTime.now(), "BOPP", Measure.CM2)
				)
		);
	}
}
