package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class TNT extends Obstacle{
    private int damage;
    private int radius;
    private int timeToBurst;
    private boolean isBurst;
    private List<Image> animation;
    public TNT(double x, double y, double sx, double sy, int width, int height){
        super(x,y,sx,sy,"src/assets/TNT1.png",width,height);
        this.isBurst= false;
        List<Image> tntAnimation = new ArrayList<>();tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT1.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT2.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT1.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT2.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT1.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT2.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT3.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT3.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT4.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT4.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT5.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT5.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT6.png").toURI().toString()));tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT6.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT7.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT7.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT8.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT8.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT9.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT9.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/blank.png").toURI().toString()));
        this.animation=tntAnimation;


        //others need to be added
    }
    public void tntPlay(){
        TNT a=this;
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(1000));}
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(a.animation.size()-1));
                a.getImg().setImage(a.animation.get(index));

            }
        };
        this.getImg().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            animation.play();
        });
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
