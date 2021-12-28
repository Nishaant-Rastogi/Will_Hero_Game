package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class Island extends Game_objects implements Jumpable{
    private static ArrayList<String> paths= new ArrayList<String>(Arrays.asList("src/assets/island_1.png","src/assets/island_2.png", "src/assets/island_3.png","src/assets/island_4.png","src/assets/island_5.png","src/assets/island_6.png"));
    private Game_objects object;
    private ArrayList<Orc> orcs;
    private int base;
    private boolean orcRevive;

    public Island(double x, double y, double sx, double sy, int path, int width, int height, int base, Game_objects game_object){
        super(x,y,sx,sy, paths.get(path), width,height);
        this.base=base;
        this.orcs=new ArrayList<>();
        object = game_object;
        orcRevive=false;
    }

    public boolean isOrcRevive() {
        return orcRevive;
    }

    public void setOrcRevive(boolean orcRevive) {
        this.orcRevive = orcRevive;
    }

    public Game_objects getObject() {
        return object;
    }
    public void setOrcs(ArrayList<Orc> orcs){
        this.orcs = orcs;
    }

    public ArrayList<Orc> getOrcs() {
        return orcs;
    }

    public int getBase() {
        return base;
    }
    @Override
    public boolean collide(Game_objects game_objects){
        return false;
    }
    @Override
    public void jump(){
        this.getImg().setY(this.getImg().getY()-this.getSpeedY());
        if(this.getImg().getY()>=this.getPosition()[1]+50 || this.getImg().getY()<=this.getPosition()[1]-50){
            double speed = this.getSpeedY();
            this.setSpeedY(-speed);
        }
    }

}
