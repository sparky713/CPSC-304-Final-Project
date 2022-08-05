package model;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private final String username;
    private final String email;
    private final String password;
    private final String displayName;
    private Map<String,Integer> foodInventory;


    public Player(String username, String email, String password, String displayName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        // Hashmap "food" stores the names of food the player owns, as well as each corresponding quantity
        this.foodInventory = new HashMap<String,Integer>();
    }

    // getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void consumes(Food food, int amount){
        String foodName = food.getFoodName();
        if (foodInventory.containsKey(foodName)){
            int currQuantity = foodInventory.get(foodName);
            foodInventory.put(foodName, currQuantity + amount);
        } else {
            foodInventory.put(foodName, amount);
        }
    }

}
