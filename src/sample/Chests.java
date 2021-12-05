package sample;

public abstract class Chests extends Game_objects implements Jumpable{
    private boolean isOpen;
    public Chests(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
        this.isOpen= false;
    }
    public void setIsOpen(){
        this.isOpen=true;
    }
    public boolean getIsOpen(){
        return this.isOpen;

    }
    @Override
    public void jump(){

    }

}
