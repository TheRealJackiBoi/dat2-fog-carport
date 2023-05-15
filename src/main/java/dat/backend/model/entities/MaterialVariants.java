package dat.backend.model.entities;

public class MaterialVariants {

    private int variantId;
    private double length;
    private int quantity;
    private int materialsId;
    private int itemListId;

    public MaterialVariants(int variantId, double length, int quantity, int materialsId, int itemListId) {
        this.variantId = variantId;
        this.length = length;
        this.quantity = quantity;
        this.materialsId = materialsId;
        this.itemListId = itemListId;
    }

    public int getVariantId() {
        return variantId;
    }

    public double getLength() {
        return length;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMaterialsId() {
        return materialsId;
    }

    public int getItemListId() {
        return itemListId;
    }
}
