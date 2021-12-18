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
    public boolean collide(Game_objects game_objects){
        if(game_objects instanceof Hero) {
            Hero h1 = (Hero) game_objects;
            if (this.getImg().getBoundsInLocal().intersects(game_objects.getImg().getBoundsInLocal())) {
                if ((this.getImg().getX() <= h1.getImg().getX()) || (this.getImg().getX() + 70 >= h1.getImg().getX() + 55)) {
                    if (this.getImg().getY() + 70 <= h1.getImg().getY()) {
                        //System.out.println("Hero under orc");

                        this.kill(h1);
                    }
                }
            }
        }
        return false;
    }
    @Override
    public void jump() {
        if(this instanceof Boss_orc) {
            if (this.getImg().getY() - this.getSpeedy() >= currIsland.getImg().getY() + currIsland.getBase() - 80) {
                this.getImg().setY(currIsland.getImg().getY() + currIsland.getBase() - 80);
                double speed = this.getSpeedy();
                this.setSpeedy(-speed);
            } else {
                this.getImg().setY(this.getImg().getY() - this.getSpeedy());
            }
            if (this.getImg().getY() <= currIsland.getImg().getY() + currIsland.getBase() - 200) {
                this.getImg().setY(currIsland.getImg().getY() + currIsland.getBase() - 200);
                double speed = this.getSpeedy();
                this.setSpeedy(-speed);
            }
        }else{
            if (this.getImg().getY() - this.getSpeedy() >= currIsland.getImg().getY() + currIsland.getBase()) {
                this.getImg().setY(currIsland.getImg().getY() + currIsland.getBase());
                double speed = this.getSpeedy();
                this.setSpeedy(-speed);
            } else {
                this.getImg().setY(this.getImg().getY() - this.getSpeedy());
            }
            if (this.getImg().getY() <= currIsland.getImg().getY() + currIsland.getBase() - 150) {
                this.getImg().setY(currIsland.getImg().getY() + currIsland.getBase() - 150);
                double speed = this.getSpeedy();
                this.setSpeedy(-speed);
            }
        }
    }
    public void die(){

    }
    public void kill(Hero h){
        //this needs to be changed
        System.exit(0);
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
