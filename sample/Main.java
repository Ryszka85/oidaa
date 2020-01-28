package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FxGUI/fxml/sample.fxml"));
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

           /* final String adrian = "Adrian";
            String ryszka = "Ryszka";
            int a = 33;


            Person p = new Person(1, adrian, ryszka, new Date());
            System.out.println(p.toString());
            ShoppingCartDbService sDb = new ShoppingCartDbService();

            List<ShoppingCart> shoppingCarts = ShoppingCartDbService.getShoppingCartByCustomerId(p);
            ProductDbService pd = new ProductDbService();
            ProductDbService.productsByShoppingCardId(p.getId())
                    .forEach(pr -> System.out.println(pr.toString()));
            *//*shoppingCarts.forEach(x -> System.out.println(x.getItems()
                    .toString()));*//*
            System.out.println(Objects.requireNonNull(AddressDbService.getUserAddress(1))
                    .toString());*/

            launch(args);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
