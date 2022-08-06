package model;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String userName;
    private String email;
    private String password;
    private String displayName;
    private Map<String,Integer> foodInventory;


    public Player(String userName, String email, String password, String displayName) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        // Hashmap "food" stores the names of food the player owns, as well as each corresponding quantity
        this.foodInventory = new HashMap<String,Integer>();
    }

    // getters
    public String getUsername() {
        return userName;
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

    // setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
