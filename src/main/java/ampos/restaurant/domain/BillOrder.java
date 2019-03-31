package ampos.restaurant.domain;

import io.swagger.models.auth.In;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Entity(value = "BillOrder", noClassnameStored = true)
public class BillOrder {
    @Id
    private ObjectId id;
    private Integer billNo;
    private ObjectId menuItem;
    private Integer quantity;
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

    public ObjectId getMenuItem() {
        return menuItem;
    }

    public BillOrder setMenuItem(ObjectId menuItem) {
        this.menuItem = menuItem;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BillOrder setQuantity(Integer quantity) {
        this.quantity = quantity;
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
