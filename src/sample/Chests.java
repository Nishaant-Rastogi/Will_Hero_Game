package sample;

public abstract class Chests extends Game_objects implements Jumpable{
    private boolean isOpen;
    public Chests(){
        //super();
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
