package org.gdas.bigreportsapi;

import org.gdas.bigreportsapi.model.entity.Component;
import org.gdas.bigreportsapi.model.enummeration.Measure;
import org.gdas.bigreportsapi.repository.ComponentsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ShopAdminAPIApplication implements CommandLineRunner {

	private final ComponentsRepository repository;

	public ShopAdminAPIApplication(ComponentsRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopAdminAPIApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.saveAll(
				List.of(
						new Component("Papel A4", Measure.UNITY),
						new Component("Papel A5", Measure.UNITY),
						new Component("Papel Fotográfico", Measure.UNITY),
						new Component("Papel Holler", Measure.UNITY),
						new Component("BOPP", Measure.CM2)
				)
		);
	}
}
