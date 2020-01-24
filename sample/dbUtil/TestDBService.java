package sample.dbUtil;

import sample.Datamodel.CartItem;
import sample.Datamodel.Person;
import sample.Datamodel.Product;
import sample.Datamodel.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDBService {
    private MysqlConnect connection;
    private Connection conn;
    private PreparedStatement selectAllFromUser;
    private PreparedStatement allUsers;
    private PreparedStatement getShoppingCart;
    private PreparedStatement getCartItems;
    private PreparedStatement getProducts;
    private final String CUSTOMER_QUERY = "SELECT * FROM person WHERE id_person = ?";
    private final String ALL_CUSTOMERS_QUERY = "SELECT * FROM person";
    private final String GET_SHOPPING_CART = "SELECT * FROM shopping_cart WHERE customer_id = ?";
    private final String GET_CART_ITEMS = "SELECT * FROM cart_position WHERE card_id = ?";
    private final String GET_PRODUCTS = "SELECT * FROM product WHERE id_product = ?";


    public TestDBService() {
        this.conn = MysqlConnect.getInstance().connect();
        try {
            this.selectAllFromUser = conn.prepareStatement(CUSTOMER_QUERY);
            this.allUsers = conn.prepareStatement(ALL_CUSTOMERS_QUERY);
            this.getShoppingCart = conn.prepareStatement(GET_SHOPPING_CART);
            this.getCartItems = conn.prepareStatement(GET_CART_ITEMS);
            this.getProducts = conn.prepareStatement(GET_PRODUCTS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<CartItem> getCartItems(final int card_id, final Product product) {
        List<CartItem> cartItems = new ArrayList<>();
        try {
            this.getCartItems.setInt(1, card_id);
            ResultSet itemResultSet = this.getCartItems.executeQuery();
            testus(product, cartItems, itemResultSet);
            return cartItems;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void testus(Product product, List<CartItem> cartItems, ResultSet itemResultSet) throws SQLException {
        while (itemResultSet.next()) {
            int id = itemResultSet.getInt("product_id");
            int quantity = itemResultSet.getInt("quantity");
            float discount = itemResultSet.getFloat("discount");
            cartItems.add( new CartItem(product, quantity, discount) );
        }
    }

    public Product getProductById(final int product_id) {
        try {
            this.getProducts.setInt(1, product_id);
            ResultSet productSet = this.getProducts.executeQuery();
            if (productSet.next()) {
                String name = productSet.getString("product_name");
                float price = productSet.getFloat("price");
                String description = productSet.getString("description");
                return new Product(product_id, name, description, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public List<Person> getAllCustomers() {
        List<Person> personList = new ArrayList<>();
        try {
            ResultSet resultSet = this.allUsers.executeQuery();
            while (resultSet.next()) {
                personList.add(
                        new Person(
                                resultSet.getInt("id_person"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getDate("birth_date"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public Person getCustomerById(final int id) {
        try {
            this.selectAllFromUser.setInt(1, id);
            ResultSet rs = this.selectAllFromUser.executeQuery();
            if (rs.next()) {
                return new Person(
                        rs.getInt("id_person"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

}
