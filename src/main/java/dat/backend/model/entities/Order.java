package dat.backend.model.entities;

/**
 * The type Order.
 */
public class Order {

    private int orderId;
    private double materialCost;
    private  double salesPrice;
    private double carportWidth;
    private double carportLength;
    private double carportHeight;
    private int userId;
    private String status;
    private double shedWidth;
    private double shedLength;
    private String userEmail;

    /**
     * Instantiates a new Order.
     *
     * @param orderId       the order id
     * @param materialCost  the material cost
     * @param salesPrice    the sales price
     * @param carportWidth  the carport width
     * @param carportLength the carport length
     * @param carportHeight the carport height
     * @param userId        the user id
     * @param status        the status
     */
//construktor for carport without a shed
    public Order(int orderId, double materialCost, double salesPrice, double carportWidth, double carportLength, double carportHeight, int userId, String status) {
        this.orderId = orderId;
        this.materialCost = materialCost;
        this.salesPrice = salesPrice;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.carportHeight = carportHeight;
        this.userId = userId;
        this.status = status;
    }

    /**
     * Instantiates a new Order.
     *
     * @param orderId       the order id
     * @param materialCost  the material cost
     * @param salesPrice    the sales price
     * @param carportWidth  the carport width
     * @param carportLength the carport length
     * @param carportHeight the carport height
     * @param userId        the user id
     * @param status        the status
     * @param shedWidth     the shed width
     * @param shedLength    the shed length
     */
    public Order(int orderId, double materialCost, double salesPrice, double carportWidth, double carportLength, double carportHeight, int userId, String status, double shedWidth, double shedLength) {
        this.orderId = orderId;
        this.materialCost = materialCost;
        this.salesPrice = salesPrice;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.carportHeight = carportHeight;
        this.userId = userId;
        this.status = status;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    /**
     * Instantiates a new Order.
     *
     * @param orderId       the order id
     * @param materialCost  the material cost
     * @param salesPrice    the sales price
     * @param carportWidth  the carport width
     * @param carportLength the carport length
     * @param carportHeight the carport height
     * @param userId        the user id
     * @param status        the status
     * @param shedWidth     the shed width
     * @param shedLength    the shed length
     * @param userEmail     the user email
     */
    public Order(int orderId, double materialCost, double salesPrice, double carportWidth, double carportLength, double carportHeight, int userId, String status, double shedWidth, double shedLength, String userEmail) {
        this.orderId = orderId;
        this.materialCost = materialCost;
        this.salesPrice = salesPrice;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.carportHeight = carportHeight;
        this.userId = userId;
        this.status = status;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.userEmail = userEmail;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Gets material cost.
     *
     * @return the material cost
     */
    public double getMaterialCost() {
        return materialCost;
    }

    /**
     * Gets sales price.
     *
     * @return the sales price
     */
    public double getSalesPrice() {
        return salesPrice;
    }

    /**
     * Gets carport width.
     *
     * @return the carport width
     */
    public double getCarportWidth() {
        return carportWidth;
    }

    /**
     * Gets carport length.
     *
     * @return the carport length
     */
    public double getCarportLength() {
        return carportLength;
    }

    /**
     * Gets carport height.
     *
     * @return the carport height
     */
    public double getCarportHeight() {
        return carportHeight;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets shed width.
     *
     * @return the shed width
     */
    public double getShedWidth() {
        return shedWidth;
    }

    /**
     * Gets shed length.
     *
     * @return the shed length
     */
    public double getShedLength() {
        return shedLength;
    }

    /**
     * Gets user email.
     *
     * @return the user email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets user email.
     *
     * @param userEmail the user email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}

