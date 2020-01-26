package sample.Datamodel;



public class CartItem {
    private int amount;
    private float discount;
    private Product product;


    public CartItem(Product product, int amount, float discount) {
        this.amount = amount;
        this.discount = discount;
        this.product = product;
    }

    public float getCost() { return (amount * product.getPrice()) * (1 - discount); }

    @Override
    public String toString() {
        return "CartItem{" +
                " amount=" + amount +
                ", discount=" + discount +
                ", " + product.toString() +
                "}";
    }
}
