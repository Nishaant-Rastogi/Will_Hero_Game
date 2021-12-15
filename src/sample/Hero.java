package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;


public class Hero extends Game_objects implements Jumpable {
    private int location;
    private Weapon weapon;
    private Helmet helmet;
    private ArrayList<Coin> coinCase;
    private boolean isAlive;
    private ArrayList<Image> movement;
    private Transition move;
    private Island currIsland;
    private boolean isRevived;
    Hero(){}

    public boolean getIsRevived() {
        return isRevived;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    Hero(double x, double y, double sx, double sy, int width, int height, Island currIsland){
        super(x,y,sx,sy,"src/assets/Gladiator.png",width,height);
        this.movement = new ArrayList<>();
        this.currIsland = currIsland;
        this.isAlive=true;
        this.isRevived=false;
        this.movement.add(new Image(new File("src/assets/MovingHero.png").toURI().toString()));
        this.movement.add(new Image(new File("src/assets/Gladiator.png").toURI().toString()));
        this.move = new Transition() {
            {setCycleDuration(Duration.millis(150));}
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction * (movement.size() - 1));
                if (index == 0) {
                    getImg().setFitWidth(100);
                }else{
                    getImg().setFitWidth(55);
                }
                getImg().setImage(movement.get(index));

            }
        };
    }

    @Override
    public void collide(Game_objects game_objects) {

    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public ArrayList<Coin> getCoinCase() {
        return coinCase;
    }

    public Island getCurrIsland() {
        return currIsland;
    }

    public void setCurrIsland(Island currIsland) {
        this.currIsland = currIsland;
    }

    public Transition getMove() {
        return move;
    }

    @Override
    public void jump() {
        try {
            if(this.getImg().getY()-this.getSpeedy() > currIsland.getImg().getY()+currIsland.getBase()){
                this.getImg().setY(currIsland.getImg().getY()+currIsland.getBase());
                double speed = this.getSpeedy();
                this.setSpeedy(-speed);
            }else {
                this.getImg().setY(this.getImg().getY() - this.getSpeedy());
            }
            if(this.getImg().getY()<=currIsland.getImg().getY()+currIsland.getBase()-100){
                this.getImg().setY(currIsland.getImg().getY()+currIsland.getBase()-100);
                double speed = this.getSpeedy();
                this.setSpeedy(-speed);
            }
        }catch(NullPointerException ignored) {

        }
    }

}