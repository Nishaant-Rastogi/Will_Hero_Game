package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;

public abstract class Weapon extends Game_objects implements Serializable {
    private int level;
    private int damage;
    private final int radius;
    private final String path;
    private final int height;
    private final int width;
    Weapon(int level, int damage, int radius, double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
        this.level = level;
        this.damage = damage;
        this.radius = radius;
        this.path = path;
        this.height = height;
        this.width = width;
    }
    public void setActive(){

    }


    public abstract void useWeapon();
    public int getLevel(){
        return level;
    }
    public void upgradeWeapon(int n){
        level+=n;
        this.damage=this.damage*((int)Math.ceil(Math.pow(2,n-1)));
    }
    public int getDamage(){
        return this.damage;
    }
    public int getRadius(){
        return this.radius;
    }
}

