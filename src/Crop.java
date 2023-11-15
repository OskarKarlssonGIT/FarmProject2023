
public class Crop extends Entity {
    private String type;
    private int quantity;

    public Crop(int id, String type, int quantity) {
        super(id);
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Crop ID = " + getId() + ", Type = " + type + ", Quantity = " + quantity;
    }
}
