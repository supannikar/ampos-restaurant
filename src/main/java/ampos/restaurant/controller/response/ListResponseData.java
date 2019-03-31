package ampos.restaurant.controller.response;

import java.util.List;

public class ListResponseData {
    private List<RestaurantMenuResponse> data;
    private long total;

    public List<RestaurantMenuResponse> getData() {
        return data;
    }

    public ListResponseData setData(List<RestaurantMenuResponse> data) {
        this.data = data;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public ListResponseData setTotal(long total) {
        this.total = total;
        return this;
    }
}
