package model;

// Stores information for a single character
public class Character {
    private String name;
    private int level;
    private int baseHP;
    private int baseATK;
    private ElementModel element;

    public Character(String name, ElementModel element){
        this.name = name;
        this.element = element;
        level = 0;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public int getBaseATK() {
        return baseATK;
    }

    public ElementModel getElement() {
        return element;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public void setBaseATK(int baseATK) {
        this.baseATK = baseATK;
    }
}
