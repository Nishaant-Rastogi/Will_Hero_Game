package sample;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Coin_chest extends Chests {
    private ArrayList<Coin> coins;
    public Coin_chest(double x, double y, double sx, double sy,int width, int height){
        super(x,y,sx,sy,"src/assets/Coin_Chest.png",width,height);
        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest2.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest3.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest4.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest5.png").toURI().toString()));

        coins= new ArrayList<>();

    }
    public ArrayList<Coin> getCoins(){
        return this.coins;
    }

    private void setCoins(){
        Random a= new Random();
        for(int i=0;i<5;i++){
            coins.add(new Coin(0,0,0,0,0,0));
        }
        for(int i=0;i<15;i++){
            if(a.nextInt(2)==0){
                coins.add(new Coin(0,0,0,0,0,0));
            }
        }
    }


}
