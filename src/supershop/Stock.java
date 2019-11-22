package labib;

public class Stock {
    private int id;
    private String name;
    private int quantity;
    private int supId;
    private  double price;

    public Stock(int id, String name, int quantity, double price,int supId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.supId = supId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String

                .format("Product [id=%d, Product Name=%s, Quantity=%d, Suplier's id=%d]",
                        id, name, quantity, supId);
    }
}
