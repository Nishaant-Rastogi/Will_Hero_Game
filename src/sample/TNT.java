package sample;

public class TNT extends Obstacle{
    private int damage;
    private int radius;
    private int timeToBurst;
    private boolean isBurst;
    public TNT(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
        this.isBurst= false;
        //others need to be added
    }
    public void activate(){

    }
    public int getRadius(){
        return this.radius;
    }
    public int getDamage(){
        return this.damage;
    }
    public boolean getisBurst(){
        return this.isBurst;
    }

    public int getTimeToBurst() {
        return timeToBurst;
    }
    @Override
    public void collide(Game_objects game_objects){

    }

}
