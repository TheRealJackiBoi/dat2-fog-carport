package dat.backend.model.entities;

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

    public int getOrder_id() {
        return order_id;
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

    public int getVariantId() {
        return variantId;
    }

    public double getLength() {
        return length;
    }

    public int getMaterialId() {
        return materialId;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getUnit() {
        return unit;
    }
}
