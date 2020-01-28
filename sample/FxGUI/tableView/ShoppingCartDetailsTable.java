package sample.FxGUI.tableView;

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
import sample.database.dbUtil.ShoppingCartDbService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ShoppingCartDetailsTable extends UserDetailsTable {
    private SimpleIntegerProperty shoppingCartId;
    private Date orderDate;
    private SimpleIntegerProperty position;
    private SimpleIntegerProperty amount;
    private SimpleDoubleProperty discount;
    private SimpleStringProperty productName;
    private SimpleStringProperty productDescription;
    private List<ShoppingCart> shoppingCarts;

    @FXML
    private static TableColumn<ShoppingCartDetailsTable, Integer> cartIdColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsTable, Date> orderDateColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsTable, Integer> positionColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsTable, Integer> quantityColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsTable, Double> discountColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsTable, String> productNameColumn;
    @FXML
    private static TableColumn<ShoppingCartDetailsTable, String> productDescriptionColumn;


    private static ObservableList<ShoppingCartDetailsTable> shoppingCartDetails;
    private TableView<ShoppingCartDetailsTable> tableViewDetails;

    static {
        shoppingCartDetails = FXCollections.observableArrayList();
    }


    public ShoppingCartDetailsTable(int shoppingCartId,
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


    public ShoppingCartDetailsTable(TableView<ShoppingCartDetailsTable> test) {
        this.tableViewDetails = test;

    }
    @Override
    public void setColumnNames() {
        initOrderIdColumnName();
        initOrderDateColumnName();
        initPositionColumn();
        initDiscountColumn();
        initProductNameColumn();
        initDescriptionColumn();
        initQuantityColumn();

        this.tableViewDetails.getColumns()
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

    private void initDescriptionColumn() {
        productDescriptionColumn = new TableColumn<>("Description");
        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
    }

    private void initProductNameColumn() {
        productNameColumn = new TableColumn<>("Product name");
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
    }

    private void initDiscountColumn() {
        discountColumn = new TableColumn<>("Discount");
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
    }

    private void initPositionColumn() {
        positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
    }

    public static ObservableList<ShoppingCartDetailsTable> getShoppingCartTableData(final int customerID) {
        List<ShoppingCartDetailsTable> shoppingCartDetailsFX = new ArrayList<>();
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
                        new ShoppingCartDetailsTable(
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
    @Override
    public void setTableViewDetails(int id) {
        this.tableViewDetails.setItems(ShoppingCartDetailsTable.getShoppingCartTableData(id));
    }

    private void initQuantityColumn() {
        quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void initOrderDateColumnName() {
        orderDateColumn = new TableColumn<>("Order date");
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    }

    private void initOrderIdColumnName() {
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
