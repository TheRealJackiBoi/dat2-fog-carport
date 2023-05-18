package dat.backend.model.entities;

public class LinkItemListMaterialVariants {

    private int itemListId;
    private int materialVariantsId;

    public LinkItemListMaterialVariants(int itemListId, int materialVariantsId) {
        this.itemListId = itemListId;
        this.materialVariantsId = materialVariantsId;
    }

    public int getItemListId() {
        return itemListId;
    }

    public int getMaterialVariantsId() {
        return materialVariantsId;
    }
}
