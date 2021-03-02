package org.sci.myshop.dao;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long productId;

    public String name;

    public String category;

    public String description;

    public String manufacturer;

    public String pictureLocation;

    public double price;

   public long getProductId() {
      return productId;
   }

   public void setProductId(long productId) {
      this.productId = productId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getManufacturer() {
      return manufacturer;
   }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }

    public void setManufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
       this.price = price;
   }
}
