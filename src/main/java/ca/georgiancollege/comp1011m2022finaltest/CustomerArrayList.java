package ca.georgiancollege.comp1011m2022finaltest;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class CustomerArrayList {
    @SerializedName("Customers")
    private Customer[] customers;

    @SerializedName("BusinessName")
    private String finalExam;

    public Customer[] getCustomers() {
        return customers;
    }

    public String getFinalExam() {
        return finalExam;
    }
}
