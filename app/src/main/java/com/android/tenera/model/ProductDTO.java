package com.android.tenera.model;

import com.shopify.buy.model.Product;
import com.shopify.buy.model.ProductVariant;

/**
 * Created by prajwalrai on 07/07/16.
 */
public class ProductDTO extends Product {
    private int quantity;
    private String price;
    private String title;
    private long weight;
    private String image;
    private ProductVariant variant;

    public ProductDTO(Product product) {
        setTitle(product.getTitle());
        setQuantity(0);
        setWeight(product.getVariants().get(0).getGrams());
        setPrice(product.getVariants().get(0).getPrice());
        setVariant(product.getVariants().get(0));
        setImage(product.getImage(variant).getSrc());
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public void setVariant(ProductVariant variant) {
        this.variant = variant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
