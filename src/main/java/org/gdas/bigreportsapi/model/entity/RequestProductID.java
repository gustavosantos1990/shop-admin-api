package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.gdas.bigreportsapi.model.json.RequestProductJSON;

import java.util.Objects;

@Embeddable
public class RequestProductID {

    @ManyToOne(optional = false)
    @JoinColumn(name = "rpd_rqt_id")
    private Request request;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rpd_pdt_id")
    private Product product;

    public RequestProductID() {
    }

    public RequestProductID(Product product) {
        this.product = product;
    }

    public RequestProductID(Request request, Product product) {
        this.request = request;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestProductID that = (RequestProductID) o;
        return Objects.equals(request, that.request) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, product);
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static RequestProductID from(RequestProductJSON source) {
        return source.getRequest() == null
                ? new RequestProductID(Product.from(source.getProduct()))
                : new RequestProductID(Request.from(source.getRequest()), Product.from(source.getProduct()));
    }
}
