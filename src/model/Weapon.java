package model;

public class Weapon {
    public String name;
    public int baseATK;
    public Weapon(String name, int baseATK) {
        this.name = name;
        this.baseATK = baseATK;
    }

    public String getName() {
        return name;
    }

    public int getBaseATK() {
        return baseATK;
    }
}
