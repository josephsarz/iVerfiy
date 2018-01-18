package com.codegene.femicodes.cscproject.model;

/**
 * Created by femicodes on 1/8/2018.
 */

public class Product {

    String productName;
    String productType;
    String nafdacNumber;
    String imageUrl;

    public Product() {
    }

    public Product(String productName, String productType, String nafdacNumber, String imageUrl) {
        this.productName = productName;
        this.productType = productType;
        this.nafdacNumber = nafdacNumber;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getNafdacNumber() {
        return nafdacNumber;
    }

    public void setNafdacNumber(String nafdacNumber) {
        this.nafdacNumber = nafdacNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
