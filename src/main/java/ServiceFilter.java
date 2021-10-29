import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceFilter {

    List<Company> companiesResult = new ArrayList<>();

    public List<Company> companyList(List<Company> companies) {
        List<Order> orders = new ArrayList<>();

        for (Company value : companies) {
            orders.add(value.getOrders().get(0));
        }

        for (Order order : orders) {
            if (companiesResult.stream().noneMatch(o -> o.getName().equals(order.getSupplier()))) {
                Company company = new Company(order.getSupplier());
                company.addOrder(order);
                companiesResult.add(company);
            } else {
                for (Company company : companiesResult) {
                    if (company.getName().equals(order.getSupplier())) {
                        company.addOrder(order);
                    }
                }
            }
        }

        for (Company company : companiesResult) {
            company.getOrders().sort(Comparator.comparing(Order::getCreated).thenComparing(Order::getPrice).reversed());
        }
        return companiesResult;
    }
}
