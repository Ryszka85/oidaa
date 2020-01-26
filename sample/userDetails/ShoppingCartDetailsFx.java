package sample.userDetails;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Datamodel.ShoppingCart;
import sample.dbUtil.ShoppingCartDbService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ShoppingCartDetailsFx {
    private SimpleIntegerProperty shoppingCartId;
    private Date orderDate;
    private SimpleIntegerProperty position;
    private SimpleIntegerProperty amount;
    private SimpleDoubleProperty discount;
    private SimpleStringProperty productName;
    private SimpleStringProperty productDescription;
    private List<ShoppingCart> shoppingCarts;

    @FXML
    private static TableColumn<ShoppingCartDetailsFx, Integer> cartIdColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsFx, Date> orderDateColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsFx, Integer> positionColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsFx, Integer> quantityColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsFx, Double> discountColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsFx, String> productNameColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsFx, String> productDescriptionColumn;

    private static ObservableList<ShoppingCartDetailsFx> shoppingCartDetails;

    static {
        shoppingCartDetails = FXCollections.observableArrayList();
    }

    public ShoppingCartDetailsFx(int shoppingCartId,
                                 Date orderDate,
                                 int position,
                                 int amount,
                                 double discount,
                                 String productName,
                                 String productDescription) {
        this.shoppingCartId = new SimpleIntegerProperty(shoppingCartId);
        this.orderDate = orderDate;
        this.position = new SimpleIntegerProperty(position);
        this.amount = new SimpleIntegerProperty(amount);
        this.discount = new SimpleDoubleProperty(discount);
        this.productName = new SimpleStringProperty(productName);
        this.productDescription = new SimpleStringProperty(productDescription);
    }

    public static void setAddressColumnNames(TableView addressTable) {
        initOrderIdColumnName();
        initOrderDateColumnName();
        initPositionColumn();
        initDiscountColumn();
        initProductNameColumn();
        initDescriptionColumn();
        initQuantityColumn();
        addressTable.getColumns()
                .setAll(
                        cartIdColumn,
                        positionColumn,
                        orderDateColumn,
                        quantityColumn,
                        discountColumn,
                        productNameColumn,
                        productDescriptionColumn
                );
    }

    private static void initDescriptionColumn() {
        productDescriptionColumn = new TableColumn<>("Description");
        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
    }

    private static void initProductNameColumn() {
        productNameColumn = new TableColumn<>("Product name");
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
    }

    private static void initDiscountColumn() {
        discountColumn = new TableColumn<>("Discount");
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
    }

    private static void initPositionColumn() {
        positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
    }

    public static ObservableList<ShoppingCartDetailsFx> setShoppingCartTableData(final int customerID) {
        /*shoppingCartDetails.setAll(ShoppingCartDbService.setShoppingCartDetailsFx(customerID));*/
        List<ShoppingCartDetailsFx> shoppingCartDetailsFX = new ArrayList<>();
        try {
            ResultSet shoppingCartDetailsResult = ShoppingCartDbService.setShoppingCartDetailsFx(customerID);
            while (Objects.requireNonNull(shoppingCartDetailsResult).next()) {
                final int card_id = shoppingCartDetailsResult.getInt("card_id");
                final Date order_date = shoppingCartDetailsResult.getDate("order_Date");
                final int position = shoppingCartDetailsResult.getInt("position");
                final int quantity = shoppingCartDetailsResult.getInt("quantity");
                final double discount = shoppingCartDetailsResult.getDouble("discount");
                final String product_name = shoppingCartDetailsResult.getString("product_name");
                final String description = shoppingCartDetailsResult.getString("description");
                shoppingCartDetailsFX.add(
                        new ShoppingCartDetailsFx(
                                card_id,
                                order_date,
                                position,
                                quantity,
                                discount,
                                product_name,
                                description
                        )
                );
            }
            shoppingCartDetails.setAll(shoppingCartDetailsFX);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingCartDetails;
    }

    private static void initQuantityColumn() {
        quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<ShoppingCartDetailsFx, Integer>("amount"));
    }

    private static void initOrderDateColumnName() {
        orderDateColumn = new TableColumn<>("Order date");
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<ShoppingCartDetailsFx, Date>("orderDate"));
    }

    private static void initOrderIdColumnName() {
        cartIdColumn = new TableColumn<>("Order id");
        cartIdColumn.setCellValueFactory(new PropertyValueFactory<>("shoppingCartId"));
    }

    public int getPosition() {
        return position.get();
    }

    public SimpleIntegerProperty positionProperty() {
        return position;
    }

    public void setPosition(int position) {
        this.position.set(position);
    }

    public int getShoppingCartId() {
        return shoppingCartId.get();
    }

    public SimpleIntegerProperty shoppingCartIdProperty() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId.set(shoppingCartId);
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public double getDiscount() {
        return discount.get();
    }

    public SimpleDoubleProperty discountProperty() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount.set(discount);
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public SimpleStringProperty productDescriptionProperty() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription.set(productDescription);
    }

}
