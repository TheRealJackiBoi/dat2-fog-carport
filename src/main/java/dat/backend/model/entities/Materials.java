package dat.backend.model.entities;

/**
 * The type Materials.
 */
public class Materials {

    /**
     * The Material Id
     */
    private int materialId;
    /**
     * The description
     * Describes what this material is
     */
    private String description;
    /**
     * The unit that is used for to measure this material
     */
    private String unit;
    /**
     * The unit price
     * Price per unit. Could be price per meter
     */
    private double unitPrice;
    /**
     * The type
     * Can for example be of the type raft or roofplate
     */
    private String type;

    /**
     * Instantiates a new Materials.
     *
     * @param materialId  the material id
     * @param description the description
     * @param unit        the unit
     * @param unitPrice   the unit price
     * @param type        the type
     */
    public Materials(int materialId, String description, String unit, double unitPrice, String type) {
        this.materialId = materialId;
        this.description = description;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.type = type;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Gets unit price.
     *
     * @return the unit price
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }
}
