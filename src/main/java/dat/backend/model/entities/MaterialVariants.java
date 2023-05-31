package dat.backend.model.entities;

/**
 * The type Material variants.
 * Builds on the Material class
 */
public class MaterialVariants {
    /**
     * The Variant Id
     */
    private int variantId;
    /**
     * The length if it has one
     */
    private double length;
    /**
     * How much one of this variant is considered to be.
     * Can for example be meters or pieces
     */
    private int quantity;
    /**
     * The Material Id
     * Which material this builds on
     */
    private int materialsId;


    /**
     * Instantiates a new Material variants.
     *
     * @param variantId   the variant id
     * @param length      the length
     * @param quantity    the quantity
     * @param materialsId the materials id
     */
    public MaterialVariants(int variantId, double length, int quantity, int materialsId) {
        this.variantId = variantId;
        this.length = length;
        this.quantity = quantity;
        this.materialsId = materialsId;
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
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets materials id.
     *
     * @return the materials id
     */
    public int getMaterialsId() {
        return materialsId;
    }

}
