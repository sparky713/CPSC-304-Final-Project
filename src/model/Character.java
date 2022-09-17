package model;

// Stores information for a single character
public class Character {
    private String name;
    private int level;
    private int baseHP;
    private int baseATK;
    private String element;

    public Character(String name, int level, int baseHP, int baseATK, String element) {
        this.name = name;
        this.level = level;
        this.baseATK = baseATK;
        this.baseHP = baseHP;
        this.element = element;
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

    public String getElement() {
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
