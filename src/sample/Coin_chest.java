package sample;

import java.util.ArrayList;

public class Coin_chest extends Chests {
    private ArrayList<Coin> coins;
    public Coin_chest(double x, double y, double sx, double sy,int width, int height){
        super(x,y,sx,sy,"src/assets/Coin_Chest",width,height);
        coins= new ArrayList<>();

    }
    public ArrayList<Coin> getCoins(){
        return this.coins;
    }
    @Override
    public void collide(Game_objects game_objects){

    }


}
