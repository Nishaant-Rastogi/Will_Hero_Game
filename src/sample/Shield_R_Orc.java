package sample;

public class Shield_R_Orc extends Orc{
    Shield_R_Orc(double x, double y, double sx, double sy, int width, int height){
        super(x,y,sx,sy,"src/assets/Red_Standard_Orc.png",width,height);
    }
    @Override
    public boolean collide(Game_objects game_objects){
        return false;
    }

}
