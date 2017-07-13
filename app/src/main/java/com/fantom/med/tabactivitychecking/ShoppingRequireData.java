package com.fantom.med.tabactivitychecking;

/**
 * Created by Admin on 7/13/2017.
 */

public class ShoppingRequireData {

    private int productId;
    private String productName;
    private String productQuintity;
    private String productPricePerKg;
    private String productTotalPrice;

    public ShoppingRequireData(int productId, String productName, String productQuintity, String productPricePerKg, String productTotalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productQuintity = productQuintity;
        this.productPricePerKg = productPricePerKg;
        this.productTotalPrice = productTotalPrice;
    }

    public ShoppingRequireData(String productName, String productQuintity, String productPricePerKg, String productTotalPrice) {
        this.productName = productName;
        this.productQuintity = productQuintity;
        this.productPricePerKg = productPricePerKg;
        this.productTotalPrice = productTotalPrice;
    }

    public ShoppingRequireData() {
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductQuintity() {
        return productQuintity;
    }

    public String getProductPricePerKg() {
        return productPricePerKg;
    }

    public String getProductTotalPrice() {
        return productTotalPrice;
    }
    public String  setProductTotalPrice(String productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
        return productTotalPrice;
    }
}