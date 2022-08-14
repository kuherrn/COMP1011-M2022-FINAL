package ca.georgiancollege.comp1011m2022finaltest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Customer {
    @SerializedName("id")
    private int id;

    @SerializedName("first name")
    private String firstName;

    @SerializedName("last name")
    private String lastName;

    @SerializedName("phone")
    private String phone;

    @SerializedName("products")
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
}
