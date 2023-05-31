package dat.backend.model.entities;

/**
 * The type Link item list material variants.
 * Links item list and material variants together
 */
public class LinkItemListMaterialVariants {

    private int itemListId;
    private int materialVariantsId;

    /**
     * Instantiates a new Link item list material variants.
     *
     * @param itemListId         the item list id
     * @param materialVariantsId the material variants id
     */
    public LinkItemListMaterialVariants(int itemListId, int materialVariantsId) {
        this.itemListId = itemListId;
        this.materialVariantsId = materialVariantsId;
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
     * Gets material variants id.
     *
     * @return the material variants id
     */
    public int getMaterialVariantsId() {
        return materialVariantsId;
    }
}
