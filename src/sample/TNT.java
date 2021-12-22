package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class TNT extends Obstacle {
    private int damage;
    private int radius;
    private int timeToBurst;
    private boolean isBurst;
    private List<Image> animation;

    public TNT(double x, double y, double sx, double sy, int width, int height) {
        super(x, y, sx, sy, "src/assets/TNT1.png", width, height);
        this.isBurst = false;
        List<Image> tntAnimation = new ArrayList<>();
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT1.png").toURI().toString()));
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
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT6.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT6.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT7.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT7.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT8.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT8.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT9.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/TNT9.png").toURI().toString()));
        tntAnimation.add(new javafx.scene.image.Image(new File("src/assets/blank.png").toURI().toString()));
        this.animation = tntAnimation;
        this.radius=50;


        //others need to be added
    }

    public void tntPlay() {
        TNT a = this;
        Transition animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1500));
            }

            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction * (a.animation.size() - 1));
                a.getImg().setImage(a.animation.get(index));

            }
        };
        System.out.println("TNT animation ");
            animation.play();

    }
    public void activate(){

    }

    public boolean activate(Hero h1) {
        //hopefully works
        isBurst=true;
        ArrayList<Orc> orcs = h1.getCurrIsland().getOrcs();
        tntPlay();
        for(int i=0;i<10000000;i++);
        for (int i=0;i<orcs.size();i++) {
            if (((this.getposition()[0] - (orcs.get(i).getImg().getX() + 70)) <= radius) || ((orcs.get(i).getImg().getX() - this.getposition()[0] + 70) <= radius)) {
                orcs.get(i).orcDeathAnimation();
                orcs.remove(i);
                i--;
            }
        }
            if(((h1.getImg().getX()-(this.getposition()[0]+70))<=radius)||((this.getposition()[0]-h1.getImg().getX()-55)<=radius)){
                return true;
                //if hero dies
            }

        return false;

    }

    public int getRadius() {
        return this.radius;
    }

    public int getDamage() {
        return this.damage;
    }

    public boolean getisBurst() {
        return this.isBurst;
    }

    public int getTimeToBurst() {
        return timeToBurst;
    }

    @Override
    public boolean collide(Game_objects game_objects) {
        Hero h1 = (Hero) game_objects;
        if (this.getImg().getBoundsInLocal().intersects(h1.getImg().getBoundsInLocal())) {
            return true;
        } else {
            return false;
        }

    }
}
