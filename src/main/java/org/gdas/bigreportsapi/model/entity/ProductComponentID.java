package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ProductComponentID {

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name="pco_rvs_number", referencedColumnName="rvs_number"),
            @JoinColumn(name="pco_rvs_pdt_id", referencedColumnName="rvs_pdt_id")
    })
    private Revision revision;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pco_cmp_id")
    private Component component;

    public ProductComponentID() {
    }

    public ProductComponentID(Revision revision, Component component) {
        this.revision = revision;
        this.component = component;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
