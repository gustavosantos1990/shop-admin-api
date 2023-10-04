package org.gdas.bigreportsapi;

import org.gdas.bigreportsapi.model.entity.*;
import org.gdas.bigreportsapi.model.enummeration.Measure;
import org.gdas.bigreportsapi.repository.ComponentRepository;
import org.gdas.bigreportsapi.repository.ProductComponentRepository;
import org.gdas.bigreportsapi.repository.ProductRepository;
import org.gdas.bigreportsapi.repository.RevisionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class ShopAdminAPIApplication implements CommandLineRunner {

	private final ComponentRepository componentRepository;
	private final ProductRepository productRepository;
	private final RevisionRepository revisionRepository;
	private final ProductComponentRepository productComponentRepository;

	public ShopAdminAPIApplication(ComponentRepository componentRepository, ProductRepository productRepository, RevisionRepository revisionRepository, ProductComponentRepository productComponentRepository) {
		this.componentRepository = componentRepository;
		this.productRepository = productRepository;
		this.revisionRepository = revisionRepository;
		this.productComponentRepository = productComponentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopAdminAPIApplication.class, args);
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
		Product agenda = productRepository.save(new Product("Agenda"));

		RevisionID revisionID = new RevisionID(1, agenda);

		Revision newRevision = new Revision();
		newRevision.setRevisionID(revisionID);
		newRevision.setName("04102023A");

		Revision revision = revisionRepository.save(newRevision);

		components.forEach(cmp -> {
			ProductComponent productComponent = new ProductComponent();
			productComponent.setProductComponentID(new ProductComponentID(revision, cmp));
			productComponent.setUnitaryValue(BigDecimal.ONE);
			productComponent.setAmount(BigDecimal.TEN);

			productComponentRepository.save(productComponent);
		});



	}
}
