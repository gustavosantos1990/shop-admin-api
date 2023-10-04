package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;

@Embeddable
public class RevisionID {

    @Column(name = "rvs_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @OneToOne(optional = false)
    @JoinColumn(name = "rvs_pdt_id")
    private Product product;

    public RevisionID() {
    }

    public RevisionID(Integer number, Product product) {
        this.number = number;
        this.product = product;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
