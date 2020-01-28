package sample.database.dbUtil;

import sample.Datamodel.Person;
import sample.Datamodel.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ShoppingCartDbService {
    private static Connection conn;
    private ResultSet resultSet;
    private static PreparedStatement getShoppingCart;
    private static final String GET_SHOPPING_CART = "SELECT * FROM shopping_cart WHERE customer_id = ?";
    private static PreparedStatement shoppingCartDetails;
    private static final String GET_SHOPPING_CART_DETAILS =
            "SELECT card_id,\n" +
                    "       order_date,\n" +
                    "       position,\n" +
                    "       quantity,\n" +
                    "       discount,\n" +
                    "       product_name,\n" +
                    "       description\n" +
                    "FROM product\n" +
                    "         INNER JOIN cart_position cp on product.id_product = cp.product_id\n" +
                    "         INNER JOIN shopping_cart sc on cp.card_id = sc.id_cart\n" +
                    "WHERE card_id IN (SELECT id_cart FROM shopping_cart WHERE customer_id = ?)";

    public ShoppingCartDbService() { }

      
    static {
        conn = MysqlConnect.getInstance().connect();
        try {
            shoppingCartDetails = conn.prepareStatement(GET_SHOPPING_CART_DETAILS);
            getShoppingCart = conn.prepareStatement(GET_SHOPPING_CART);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet setShoppingCartDetailsFx(final int customerID) {
        try {
            shoppingCartDetails.setInt(1, customerID);
            ResultSet shoppingCartDetailsResult = shoppingCartDetails.executeQuery();
            return shoppingCartDetails.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
