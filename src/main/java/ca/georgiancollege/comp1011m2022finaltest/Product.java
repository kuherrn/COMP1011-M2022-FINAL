package ca.georgiancollege.comp1011m2022finaltest;

import com.google.gson.annotations.SerializedName;


public class Product {

  @SerializedName("sku")
  private String sku;

  @SerializedName("name")
  private String name;

  @SerializedName("salePrice")
  private float salePrice;

  @SerializedName("regularPrice")
  private float regularPrice;

  @SerializedName("image")
  private String poster;

  public String getSku() {
    return sku;
  }

  public String getName() {
    return name;
  }

  public float getSalePrice() {
    return salePrice;
  }

  public float getRegularPrice() {
    return regularPrice;
  }

  public String getPoster() {
    return poster;
  }

  @Override
  public String toString() {
    return String.format("%s - $%f", this.getName(), this.getRegularPrice());
  }
}
