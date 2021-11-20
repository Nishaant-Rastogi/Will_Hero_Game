package sample;

public class Island extends Game_objects implements Jumpable{
    private int length;
    public Island(int l){
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
