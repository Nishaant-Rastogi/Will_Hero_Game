package sample;

import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


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
            {setCycleDuration(Duration.millis(200));}
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction * (movement.size() - 1));
                if (index == 0) {
                    getImg().setFitWidth(100);
                    if(weapon != null)weapon.getImg().setX(weapon.getImg().getX()+50);
                }else{
                    getImg().setFitWidth(55);
                    if(weapon != null)weapon.getImg().setX(weapon.getImg().getX()-50);
                }
                getImg().setImage(movement.get(index));

            }
        };
        coinCase = new ArrayList<>();
    }

    @Override
    public boolean collide(Game_objects game_objects) {
        return false;
    }

    public void setWeapon(Weapon weapon, Group root) {
        if(this.weapon != null) {
            root.getChildren().remove(root.getChildren().size()-3);
        }
        this.weapon = weapon;
        if(weapon instanceof Lance)this.weapon.makeImage(root, this.getImg().getX(), this.getImg().getY() + 30);
        else this.weapon.makeImage(root, this.getImg().getX(), this.getImg().getY() + 30);

    }

    public Weapon getWeapon() {
        return weapon;
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
        AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/move.mp3")).toExternalForm());
        buzzer.play();
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
    public void setRevived() {
        isRevived = true;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}