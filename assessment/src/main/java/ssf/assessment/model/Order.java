package ssf.assessment.model;

import java.io.Serializable;
import java.util.Random;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Order implements Serializable {
    
    private String orderId;

    //View 0 Fields
    @NotNull(message="Please select a pizza.")
    private String pizza;

    @NotNull(message="Please select the pizza size.")
    private String size;

    @Min(value = 1, message = "Min qty is 1.")
    @Max(value = 10, message = "Max qty is 10.")
    private Integer quantity;

    //View 1 Fields
    @NotBlank(message = "Mandatory Field.")
    @Size(min = 3, message = "Name must be at least 3 characters.")
    private String name;

    @NotBlank(message = "Mandatory Field.")
    private String address;

    @NotBlank(message = "This field is mandatory.")
    @Size(min = 8, max = 8, message = "Phone number must be 8 digits.")
    private String phone;

    private boolean rush;

    private String comments;

    private float total = 0f;

    //Empty constructor
    public Order() {
        this.orderId = this.generateId(8);
    }

    //Getters
    public String getOrderId() {
        return orderId;
    }
    public String getPizza() {
        return pizza;
    }
    public String getSize() {
        return size;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public boolean isRush() {
        return rush;
    }
    public String getComments() {
        return comments;
    }
    public float getTotal() {
        return total;
    }

    //Setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    //Method to generate randomised orderId for each order
    //Synchronous method, so that the id will be unique, taking in 1 request at a time
    private synchronized String generateId(int numOfChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numOfChars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        //Reduce string to number of required characters
        return sb.toString().substring(0, numOfChars);
    }
    
}
    

