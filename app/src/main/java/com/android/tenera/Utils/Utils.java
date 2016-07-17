package com.android.tenera.Utils;

import com.shopify.buy.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by raghavendra on 09/07/16.
 */
public class Utils {

    private static ArrayList<List<Product>> menuItems;

    public static ArrayList<String> getMenuTitles() {
        return menuTitles;
    }

    public static void setMenuTitles(ArrayList<String> menuTitles) {
        Utils.menuTitles = menuTitles;
    }

    private static ArrayList<String> menuTitles;

    public static ArrayList<List<Product>> getMenuItems() {
        if (menuItems == null){
            menuItems = new ArrayList<>();
        }
        return menuItems;
    }

    public void setMenuItems(ArrayList<List<Product>> menuItems) {
        this.menuItems = menuItems;
    }
    public static String generateRandomString() {
        String key = UUID.randomUUID().toString();
        key = key.replace("-", "");
        // Logger.error("Secret Key: " + key);
        return key;
    }
}
