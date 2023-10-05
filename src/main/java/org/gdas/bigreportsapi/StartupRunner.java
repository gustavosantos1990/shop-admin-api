package org.gdas.bigreportsapi;

import org.gdas.bigreportsapi.model.entity.Component;
import org.gdas.bigreportsapi.model.entity.Product;
import org.gdas.bigreportsapi.model.enummeration.Measure;
import org.gdas.bigreportsapi.repository.ComponentRepository;
import org.gdas.bigreportsapi.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

import static java.math.BigDecimal.TEN;

public class StartupRunner implements CommandLineRunner {

	private final ComponentRepository componentRepository;
	private final ProductRepository productRepository;

	public StartupRunner(ComponentRepository componentRepository, ProductRepository productRepository) {
		this.componentRepository = componentRepository;
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		List<Component> components = componentRepository.saveAll(
				List.of(
						new Component("Papel A4", Measure.UNITY),
						new Component("Papel A5", Measure.UNITY),
						new Component("Papel Fotográfico", Measure.UNITY),
						new Component("Papel Holler", Measure.UNITY),
						new Component("BOPP", Measure.CM2)
				)
		);

		Product agenda = productRepository.save(new Product("Agenda", null, TEN));

	}

}
