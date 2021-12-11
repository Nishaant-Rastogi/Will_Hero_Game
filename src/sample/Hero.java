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
    private ArrayList<Coin> coincase;
    private boolean isAlive;
    private ArrayList<Image> movement;
    private Transition move;
    private boolean isRevived;
    Hero(){}
    Hero(double x, double y, double sx, double sy, int width, int height){
        super(x,y,sx,sy,"src/assets/Gladiator.png",width,height);
        this.movement = new ArrayList<>();
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
                    getImg().setFitWidth(70);
                }
                getImg().setImage(movement.get(index));

            }
        };
    }

    @Override
    public void collide(Game_objects game_objects) {

    }

    public Transition getMove() {
        return move;
    }

    @Override
    public void jump() {
        this.getImg().setY(this.getImg().getY()-this.getSpeedy());
        if(this.getImg().getY()>=280 || this.getImg().getY()<=180){
            double speed = this.getSpeedy();
            this.setSpeedy(-speed);
        }
    }

}
