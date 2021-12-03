package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

public abstract class Orc extends Game_objects implements Jumpable{
    private int damage;
    private int health;
    private boolean dead;
    private ArrayList<Coin> coins;
    public Orc(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
        dead=false;
    }
    @Override
    public void jump() {
    }

    public void die(){

    }
    public void hurt(Hero h){

    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead() {
        this.dead = true;
    }
}
