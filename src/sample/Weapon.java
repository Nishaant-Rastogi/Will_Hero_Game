package sample;

public abstract class Weapon {
    private int level;
    private int damage;
    private int radius;
    public abstract void useWeapon();
    public abstract void upgradeWeapon();
    public int getdamage(){
        return this.damage;
    }
    public int getRadius(){
        return this.radius;
    }
}

