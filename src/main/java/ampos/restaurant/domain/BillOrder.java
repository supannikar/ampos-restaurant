package ampos.restaurant.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.time.Instant;
import java.util.List;

@Entity(value = "BillOrder", noClassnameStored = true)
public class BillOrder {
    @Id
    private ObjectId id;
    private Integer billNo;
    private List<MenuOrderItem> menuOrderItems;
    private Double totalPrice;
    private Instant orderTime;

    public ObjectId getId() {
        return id;
    }

    public BillOrder setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public Integer getBillNo() {
        return billNo;
    }

    public BillOrder setBillNo(Integer billNo) {
        this.billNo = billNo;
        return this;
    }

    public List<MenuOrderItem> getMenuOrderItems() {
        return menuOrderItems;
    }

    public BillOrder setMenuOrderItems(final List<MenuOrderItem> menuOrderItems) {
        this.menuOrderItems = menuOrderItems;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public BillOrder setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    public BillOrder setOrderTime(Instant orderTime) {
        this.orderTime = orderTime;
        return this;
    }
}
