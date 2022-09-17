package model;

public class Abilities {
    public String aname;
    public String cname;
    public int level;
    public float cd;
    public int dmg;
    public Abilities(String aname,String cname, int level, float cd, int dmg) {
        this.aname = aname;
        this.cname = cname;
        this.level = level;
        this.cd = cd;
        this.dmg = dmg;
//        level = 0;
    }

    public String getAname() {
        return aname;
    }

    public String getCname() {
        return cname;
    }

    public int getLevel() {
        return level;
    }

    public float getCd() {
        return cd;
    }

    public int getDmg() {
        return dmg;
    }
}
