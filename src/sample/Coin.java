package sample;

public class Coin extends Game_objects{
    private int value;
    public Coin(double x, double y, double sx, double sy,int width, int height){
        super(x,y,sx,sy,"src/assets/Coin.png",width,height);
    }
    @Override
    public boolean collide(Game_objects game_objects) {
        return false;
    }

}
