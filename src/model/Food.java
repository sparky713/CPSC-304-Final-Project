package model;

public class Food {
    String name;
    int healAmount;

    public Food(String name, int healAmount){
        this.name = name;
        this.healAmount = healAmount;
    }

    public String getFoodName(){
        return name;
    }

    public int getFoodHealAmount(){
        return healAmount;
    }
}
