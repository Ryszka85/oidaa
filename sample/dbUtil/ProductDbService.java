package sample.dbUtil;

import sample.Datamodel.Product;
import sample.validator.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDbService {
    private MysqlConnect connection;
    private static Connection conn;
    private static PreparedStatement getProduct;
    private static PreparedStatement getProductByShoppingCart;
    private static final String GET_PRODUCT = "SELECT * FROM product WHERE id_product = ?";
    private static final String GET_PRODUCT_BY_SHOOPING_CART = "SELECT *  FROM product " +
            "INNER JOIN cart_position cp on product.id_product = cp.product_id " +
            "WHERE card_id IN (SELECT id_cart FROM shopping_cart WHERE customer_id = ?)";


    public ProductDbService() { }

    static {
        conn = MysqlConnect.getInstance().connect();
        try {
            getProduct = conn.prepareStatement(GET_PRODUCT);
            getProductByShoppingCart = conn.prepareStatement(GET_PRODUCT_BY_SHOOPING_CART);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> productsByShoppingCardId(final int id) {
        List<Product> products = new ArrayList<>();
        try {
            Validator.validatePositiveNumbers(id);
            getProductByShoppingCart.setInt(1, id);
            ResultSet productsSet = getProductByShoppingCart.executeQuery();
            while (productsSet.next()) {
                int id_product = productsSet.getInt("id_product");
                String name = productsSet.getString("product_name");
                float price = productsSet.getFloat("price");
                String description = productsSet.getString("description");
                products.add(new Product(id_product, name, description, price));
            }
            return Collections.unmodifiableList(products);
        } catch (SQLException e) {
            e.printStackTrace();
            return products;
        }
    }

    public static Product getProductById(final int product_id) {
        try {
            Validator.validatePositiveNumbers(product_id);
            getProduct.setInt(1, product_id);
            ResultSet productSet = getProduct.executeQuery();
            if (productSet.next()) {
                String name = productSet.getString("product_name");
                float price = productSet.getFloat("price");
                String description = productSet.getString("description");
                return new Product(product_id, name, description, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
