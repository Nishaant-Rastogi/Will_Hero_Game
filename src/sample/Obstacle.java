package sample;

public abstract class Obstacle extends Game_objects{

    Obstacle(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
    }
    public abstract void activate();
}
