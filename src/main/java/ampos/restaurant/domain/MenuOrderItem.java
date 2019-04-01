package ampos.restaurant.domain;

public class MenuOrderItem {
    private String id;
    private Integer quantity;
    private Double priceWithQuantity;

    public String getId() {
        return id;
    }

    public MenuOrderItem setId(final String id) {
        this.id = id;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public MenuOrderItem setQuantity(final Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getPriceWithQuantity() {
        return priceWithQuantity;
    }

    public MenuOrderItem setPriceWithQuantity(final Double priceWithQuantity) {
        this.priceWithQuantity = priceWithQuantity;
        return this;
    }
}
