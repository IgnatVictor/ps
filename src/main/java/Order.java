import java.time.LocalDateTime;


public class Order {

    private String description;
    private String gtin;
    private LocalDateTime created;
    private int orderId;
    private Float price;
    private String supplier;
    private String currency;


    public Order(String description, String gtin, LocalDateTime created, int orderId, Float price, String supplier, String currency) {
        this.description = description;
        this.gtin = gtin;
        this.created = created;
        this.orderId = orderId;
        this.price = price;
        this.supplier = supplier;
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public String getGtin() {
        return gtin;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public int getOrderId() {
        return orderId;
    }

    public Float getPrice() {
        return price;
    }

    public String getSupplier() {
        return supplier;
    }

}
