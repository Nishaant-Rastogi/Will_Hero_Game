package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.spi.AbstractResourceBundleProvider;

public class Island extends Game_objects implements Jumpable{
    private int length;
    private static ArrayList<String> paths= new ArrayList<String>(Arrays.asList("src/assets/island_1.png","src/assets/island_2.png", "src/assets/island_3.png","src/assets/island_4.png","src/assets/island_5.png","src/assets/island_6.png"));
    private ArrayList<Game_objects> objects;
    public Island(double x, double y, double sx, double sy, int path, int width, int height, int l){
        super(x,y,sx,sy, paths.get(path), width,height);
        this.length=l;
        objects= new ArrayList<>();
    }


    public int getLength() {
        return length;
    }
    @Override
    public void collide(Game_objects game_objects){

    }
    @Override
    public void jump(){
        this.getImg().setY(this.getImg().getY()-this.getSpeedy());
        if(this.getImg().getY()>=100 || this.getImg().getY()<=0){
            double speed = this.getSpeedy();
            this.setSpeedy(-speed);
        }
    }

}
