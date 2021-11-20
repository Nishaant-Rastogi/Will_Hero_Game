package sample;

import java.util.ArrayList;

public class Coin_chest extends Chests {
    private ArrayList<Coin> coins;
    public Coin_chest(){
        coins= new ArrayList<>();

    }
    public ArrayList<Coin> getCoins(){
        return this.coins;
    }
    @Override
    public void collide(Game_objects game_objects){

    }


}
