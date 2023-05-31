package dat.backend.model.entities;

/**
 * The type Part.
 */
public class Part {

    private int order_id;
    private String useDescription;
    private int quantity;
    private double price;
    private int variantId;
    private double length;
    private int materialId;
    private String dimensions;
    private String unit;

    /**
     * Instantiates a new Part.
     *
     * @param order_id       the order id
     * @param useDescription the use description
     * @param quantity       the quantity
     * @param price          the price
     * @param variantId      the variant id
     * @param length         the length
     * @param materialId     the material id
     * @param dimensions     the dimensions
     * @param unit           the unit
     */
    public Part(int order_id, String useDescription, int quantity, double price, int variantId, double length, int materialId, String dimensions, String unit) {
        this.order_id = order_id;
        this.useDescription = useDescription;
        this.quantity = quantity;
        this.price = price;
        this.variantId = variantId;
        this.length = length;
        this.materialId = materialId;
        this.dimensions = dimensions;
        this.unit = unit;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrder_id() {
        return order_id;
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
     * Gets variant id.
     *
     * @return the variant id
     */
    public int getVariantId() {
        return variantId;
    }

    /**
     * Gets length.
     *
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * Gets material id.
     *
     * @return the material id
     */
    public int getMaterialId() {
        return materialId;
    }

    /**
     * Gets dimensions.
     *
     * @return the dimensions
     */
    public String getDimensions() {
        return dimensions;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }
}
