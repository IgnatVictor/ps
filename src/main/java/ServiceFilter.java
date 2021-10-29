import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceFilter {

    List<Company> companiesResult = new ArrayList<>();

    public List<Company> companyList(List<Company> companies) {
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < companies.size(); i++) {
            orders.add(companies.get(i).getOrders().get(0));
        }

        for (Order order : orders) {
            if (!companiesResult.stream().anyMatch(o -> o.getName().equals(order.getSupplier()))) {
                Company company = new Company(order.getSupplier());
                company.addOrder(order);
                companiesResult.add(company);
            } else {
                for (int i = 0; i < companiesResult.size(); i++) {
                    if (companiesResult.get(i).getName().equals(order.getSupplier())) {
                        companiesResult.get(i).addOrder(order);
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
