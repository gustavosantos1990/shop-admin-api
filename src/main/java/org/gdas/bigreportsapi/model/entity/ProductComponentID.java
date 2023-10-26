package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ProductComponentID {

    @ManyToOne(optional = false)
    @JoinColumn(name = "pco_pdt_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pco_cmp_id")
    private Component component;

    public ProductComponentID() {
    }

    public ProductComponentID(Component component) {
        this.component = component;
    }

    public ProductComponentID(Product product, Component component) {
        this.product = product;
        this.component = component;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

}
