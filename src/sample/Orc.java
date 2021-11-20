package sample;

import java.util.ArrayList;

public abstract class Orc extends Game_objects implements Jumpable{
    private int damage;
    private int health;
    private boolean dead;
    private ArrayList<Coin> coins;
    public Orc(){
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
