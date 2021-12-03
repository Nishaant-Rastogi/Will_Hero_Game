package sample;

import java.util.ArrayList;

public class Hero extends Game_objects implements Jumpable {
    private int location;
    private Weapon weapon;
    private Helmet helmet;
    private ArrayList<Coin> coincase;
    private boolean isAlive;
    private boolean isRevived;
    Hero(){}
    Hero(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
    }

    @Override
    public void collide(Game_objects game_objects) {

    }

    @Override
    public void jump() {

    }

}
