package sample;

public class Boss_orc extends Orc{
    Boss_orc(double x, double y, double sx, double sy,int width, int height){
        super(x,y,sx,sy,"src/assets/Boss_orc.png",width,height);
    }
    @Override
    public boolean collide(Game_objects game_objects){
        return false;
    }
    public void move(){

    }

}
