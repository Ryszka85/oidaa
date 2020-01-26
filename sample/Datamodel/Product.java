package sample.Datamodel;


public class Product {
    private int id;
    private String productName;
    private String description;
    private float price;

    public Product(int id, String productName, String description, float price) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    public Product(String productName, String description, float price) {
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
