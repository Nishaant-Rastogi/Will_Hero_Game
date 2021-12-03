package sample;

public class Island extends Game_objects implements Jumpable{
    private int length;
    public Island(double x, double y, double sx, double sy, String path, int width, int height, int l){
        super(x,y,sx,sy,path,width,height);
        this.length=l;
    }

    public int getLength() {
        return length;
    }
    @Override
    public void collide(Game_objects game_objects){

    }
    @Override
    public void jump(){

    }

}
