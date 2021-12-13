package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

public abstract class Orc extends Game_objects implements Jumpable{
    private int damage;
    private int health;
    private boolean dead;
    private Island currIsland;
    private ArrayList<Coin> coins;
    public Orc(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
        dead=false;
    }

    public void setCurrIsland(Island currIsland) {
        this.currIsland = currIsland;
    }

    public Island getCurrIsland() {
        return currIsland;
    }

    @Override
    public void jump() {
        if(this.getImg().getY()-this.getSpeedy() >= currIsland.getImg().getY()+currIsland.getBase()){
            this.getImg().setY(currIsland.getImg().getY()+currIsland.getBase());
            double speed = this.getSpeedy();
            this.setSpeedy(-speed);
        }else {
            this.getImg().setY(this.getImg().getY() - this.getSpeedy());
        }
        if(this.getImg().getY()<=currIsland.getImg().getY()+currIsland.getBase()-100){
            this.getImg().setY(currIsland.getImg().getY()+currIsland.getBase()-100);
            double speed = this.getSpeedy();
            this.setSpeedy(-speed);
        }
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
