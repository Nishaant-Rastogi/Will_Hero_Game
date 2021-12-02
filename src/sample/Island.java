package sample;

public class Island extends Game_objects implements Jumpable{
    private int length;
    public Island(int l){
        super(50,25,0,0,"../assets/island_1.png",100,100);
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
