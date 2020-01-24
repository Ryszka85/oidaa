package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Datamodel.Person;
import sample.Datamodel.Product;
import sample.Datamodel.ShoppingCart;
import sample.dbUtil.ProductDbService;
import sample.dbUtil.ShoppingCartDbService;
import sample.dbUtil.TestDBService;
import sample.validator.PersonValidator;

import java.util.Date;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try{

            /*MysqlConnect mysqlConnect = MysqlConnect.getInstance();
            mysqlConnect.connect();
            launch(args);

            StringBuilder sb = new StringBuilder("SELECT * FROM user WHERE email = ?");
            try (PreparedStatement statement = mysqlConnect.connect().prepareStatement(sb.toString())){
                *//*statement.setInt(1, 1);*//*
                statement.setString(1, "adrian.ryszka@gmx.net");
                ResultSet rs = statement.executeQuery();
                if (rs.next()) System.out.println(rs.getString("password"));
                else System.out.println("Fuck");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysqlConnect.disconnect();
            }*/

            /*Person person = new UserServiceDb().getCustomerById(2);*/
            /*System.out.println(person.toString());*/
            /*new UserServiceDb().getAllCustomers().forEach(x -> System.out.println(x.toString()));*/
            /*new UserServiceDb().getCartItems(1);*/

            /*TestDBService userServiceDb = new TestDBService();*/


            final String adrian = "Adrian";
            String ryszka = "Ryszka";
            int a = 33;


            Person p = new Person(1, adrian, ryszka, new Date());
            System.out.println(p.toString());
            ShoppingCartDbService sDb = new ShoppingCartDbService();

            List<ShoppingCart> shoppingCarts = ShoppingCartDbService.getShoppingCartByCustomerId(p);
            ProductDbService pd = new ProductDbService();
            shoppingCarts.forEach(x -> {
                final int id = x.getId();
                ProductDbService.productsByShoppingCardId(id)
                        .forEach(pr -> x.addItem(pr, 1, 2));
            });
            shoppingCarts.forEach(x -> System.out.println(x.getItems()
                    .toString()));




           /* while (shoppingCart.next()) {
                System.out.println(shoppingCart.getString("employee_id"));
                shoppingCart1.add(new ShoppingCart(
                        person,
                        shoppingCart.getInt("id_cart"),
                        shoppingCart.getDate("order_date"))
                );
            }
            shoppingCart1.forEach(s -> System.out.println(s.toString()));*/
            launch(args);

        }catch(Exception e){ System.out.println(e);}
    }


}
