package sample;

public class Normal_G_Orc extends Orc{
    Normal_G_Orc(double x, double y, double sx, double sy, int width, int height){
        super(x,y,sx,sy,"src/assets/Green_Orc.png",width,height);
    }
    @Override
    public boolean collide(Game_objects game_objects){
        return false;
    }

}
