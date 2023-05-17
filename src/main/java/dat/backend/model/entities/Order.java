package dat.backend.model.entities;

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

    public int getOrderId() {
        return orderId;
    }

    public double getMaterialCost() {
        return materialCost;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public double getCarportWidth() {
        return carportWidth;
    }

    public double getCarportLength() {
        return carportLength;
    }

    public double getCarportHeight() {
        return carportHeight;
    }

    public int getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public double getShedWidth() {
        return shedWidth;
    }

    public double getShedLength() {
        return shedLength;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
