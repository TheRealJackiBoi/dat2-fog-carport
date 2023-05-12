package dat.backend.model.entities;

public class Materials {

    private int materialId;
    private String description;
    private String unit;
    private double unitPrice;
    private String type;

    public Materials(int materialId, String description, String unit, double unitPrice, String type) {
        this.materialId = materialId;
        this.description = description;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.type = type;
    }

    public int getMaterialId() {
        return materialId;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getType() {
        return type;
    }
}
