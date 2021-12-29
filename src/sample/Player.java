package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private int score;
    private Hero hero;
    private int key;
    public Player(int key){
        score=0;
        hero= null;
        this.key = key;
    }
    public void play(){

    }
    public void resume(){

    }
    public void restart(){

    }
    public void saveGame(){

    }
    public void pause(){

    }
    public int getScore(){
        return this.score;
    }

    public int getKey() {
        return key;
    }
}
