package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.io.File;

public abstract class Weapon {
    private int level;
    private int damage;
    private int radius;
    private String path;
    private int height;
    private int width;
    transient private ImageView img;
    Weapon(int level, int damage, int radius, String path, int width, int height){
        this.level = level;
        this.damage = damage;
        this.radius = radius;
        this.path = path;
        this.height = height;
        this.width = width;
    }
    public void setActive(){

    }
    public void makeImage(Group root,double x, double y){
        img = new ImageView(new File(path).toURI().toString());
        img.setX(x);
        img.setY(y);
        img.setFitWidth(width);
        img.setFitHeight(height);
        root.getChildren().add(root.getChildren().size()-2,img);
    }

    public ImageView getImg() {
        return img;
    }

    public abstract void useWeapon();
    public int getLevel(){
        return level;
    }
    public void upgradeWeapon(){
        level+=1;
        this.damage=this.damage*2;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getRadius(){
        return this.radius;
    }
}

