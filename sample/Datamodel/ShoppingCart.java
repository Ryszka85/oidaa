package sample.Datamodel;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;
    private Person customer;
    private int id;
    private Date orderDate;

    public ShoppingCart(Person customer, Date orderDate) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.orderDate = orderDate;
    }

    public ShoppingCart(Person customer, int cartId, Date orderDate) {
        this(customer, orderDate);
        this.id = cartId;
    }

    public void addItem(Product product, int quantity, float discount) {
        items.add(new CartItem(product, quantity, discount));
    }

    public int getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public void printItems() {
        System.out.println(this.customer.toString());
        this.items.forEach(x -> System.out.println(x.toString()));
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                '}';
    }
}
