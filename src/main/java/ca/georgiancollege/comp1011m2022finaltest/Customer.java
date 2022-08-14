package ca.georgiancollege.comp1011m2022finaltest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class Customer {
    @SerializedName("id")
    private int id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("phoneNumber")
    private String phone;

    @SerializedName("purchases")
    private Product[] products;


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Product[] getProducts() {
        return products;
    }

    public String getPurchases() {
        float salePrice = 0.0f;
        for (var product:getProducts()) {
            salePrice += product.getSalePrice();
        }
        return "$" + salePrice;
    }

    public Customer(int id, String firstName, String lastName, String phone, Product[] products) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.products = products;
    }

//    @Override
//    public String toString() {
//        String price = "";
//        for (var product:getProducts()) {
//            price += "Regular Price: " + product.getRegularPrice() + ", Sale Price:" + product.getSalePrice() + "\n";
//        }
//
//        return price;
//    }
}
