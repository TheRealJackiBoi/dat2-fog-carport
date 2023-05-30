package dat.backend.model.entities;

/**
 * The type Item list.
 */
public class ItemList {
    /**
     * The Item List Id
     * The id for the item-list that this item belongs to
     */
    private int itemListId;
    /**
     * The Use description.
     * What each item is used for like rafts or roof plates
     */
    String useDescription;
    /**
     * The Quantity.
     * The amount of each item
     */
    int quantity;
    /**
     * The Price.
     */
    double price;
    /**
     * The Order id.
     */
    int orderId;

    /**
     * Instantiates a new Item list.
     *
     * @param itemListId     the item list id
     * @param useDescription the use description
     * @param quantity       the quantity
     * @param price          the price
     * @param orderId        the order id
     */
    public ItemList(int itemListId, String useDescription, int quantity, double price, int orderId) {
        this.itemListId = itemListId;
        this.useDescription = useDescription;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
    }

    /**
     * Gets item list id.
     *
     * @return the item list id
     */
    public int getItemListId() {
        return itemListId;
    }

    /**
     * Gets use description.
     *
     * @return the use description
     */
    public String getUseDescription() {
        return useDescription;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderId() {
        return orderId;
    }
}
