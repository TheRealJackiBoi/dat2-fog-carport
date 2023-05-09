package dat.backend.model.entities;

public class ItemList {
    private int itemListId;
    String useDescription;
    int quantity;
    double price;
    int orderId;

    public ItemList(int itemListId, String useDescription, int quantity, double price, int orderId) {
        this.itemListId = itemListId;
        this.useDescription = useDescription;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
    }

    public int getItemListId() {
        return itemListId;
    }

    public String getUseDescription() {
        return useDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getOrderId() {
        return orderId;
    }
}
