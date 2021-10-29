import java.util.ArrayList;
import java.util.List;

public class Company {

    List<Order> orders;
    private String name;

    public Company(String name) {
        this.name = name;
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }

}
