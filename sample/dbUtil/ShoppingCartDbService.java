package sample.dbUtil;

import sample.Datamodel.Person;
import sample.Datamodel.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ShoppingCartDbService {
    private static Connection conn;
    private ResultSet resultSet;
    private static PreparedStatement getShoppingCart;
    private static final String GET_SHOPPING_CART = "SELECT * FROM shopping_cart WHERE customer_id = ?";

    public ShoppingCartDbService() { }

      
    static {
        conn = MysqlConnect.getInstance().connect();
        try {
            getShoppingCart = conn.prepareStatement(GET_SHOPPING_CART);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ShoppingCart> getShoppingCartByCustomerId(final Person person) {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try {
            getShoppingCart.setInt(1, person.getId());
            ResultSet rs = getShoppingCart.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_cart");
                Date orderDate = rs.getDate("order_date");
                shoppingCarts.add(new ShoppingCart(person, id, orderDate));
            }
            return Collections.unmodifiableList(shoppingCarts);
        } catch (SQLException e) {
            e.printStackTrace();
            return shoppingCarts;
        }
    }
}
