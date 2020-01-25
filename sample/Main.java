package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Datamodel.Person;
import sample.Datamodel.ShoppingCart;
import sample.dbUtil.ProductDbService;
import sample.dbUtil.ShoppingCartDbService;


import java.util.Date;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    public static void main(String[] args) {
        try {

            final String adrian = "Adrian";
            String ryszka = "Ryszka";
            int a = 33;


            Person p = new Person(1, adrian, ryszka, new Date());
            System.out.println(p.toString());
            ShoppingCartDbService sDb = new ShoppingCartDbService();

            List<ShoppingCart> shoppingCarts = ShoppingCartDbService.getShoppingCartByCustomerId(p);
            ProductDbService pd = new ProductDbService();
            /*shoppingCarts.forEach(x -> {
                final int id = x.getId();
                ProductDbService.productsByShoppingCardId(id)
                        .forEach(pr -> x.addItem(pr, 1, 2));
            });
            shoppingCarts.forEach(x -> System.out.println(x.getItems()
                    .toString()));
            System.out.println(Objects.requireNonNull(AddressDbService.getUserAddress(1))
                    .toString());*/

            launch(args);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
