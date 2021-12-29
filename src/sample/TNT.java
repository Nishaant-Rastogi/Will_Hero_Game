package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


public class TNT extends Obstacle {
    private int damage;
    private int radius;
    private int timeToBurst;
    private boolean isBurst;
    private boolean isActivate;
    private transient List<Image> animation;
    private transient Transition tntAnimation;
    public TNT(double x, double y, double sx, double sy, int width, int height) {
        super(x, y, sx, sy, "src/assets/TNT1.png", width, height);
        this.isBurst = false;
        this.isActivate=false;
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
        this.radius=20;


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
                if(a.animation == null){
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
                    a.animation = tntAnimation;
                }
                int index = (int) (fraction * (a.animation.size() - 1));

                a.getImg().setImage(a.animation.get(index));
                if(index==5){
                    a.getImg().setFitWidth(200);
                    a.getImg().setFitHeight(200);
                    isActivate=true;
                }

            }
        };
        this.tntAnimation=animation;
        this.tntAnimation.setCycleCount(1);
    }
    public void activate(){

    }

    public void activate(Hero h1) {
        //hopefully works
        ArrayList<Orc> orcs = h1.getCurrIsland().getOrcs();
        tntPlay();
        this.tntAnimation.setOnFinished(E->{
            for (int i=0;i<orcs.size();i++) {
                if ((((this.getImg().getX() - (orcs.get(i).getImg().getX() + 70)) <= radius)&&(orcs.get(i).getImg().getX()-this.getImg().getY())<=radius)|| ((((orcs.get(i).getImg().getX() - (this.getImg().getX() + 70)) <= radius))&&(orcs.get(i).getImg().getX()-this.getImg().getY())<=radius)) {
                    orcs.get(i).orcDeathAnimation();
                    orcs.remove(i);
                    i--;
                }
            }
            //hero circle equation missing as done in orc
            if(this.getImg().getBoundsInLocal().intersects(h1.getImg().getBoundsInLocal())) {
                if (((h1.getImg().getX() - (this.getImg().getX() + 70)) <= radius) || ((this.getImg().getX() - h1.getImg().getX() - 55) <= radius)) {
                    h1.setAlive(false);
                    //if hero dies
                }
            }
        });
        this.tntAnimation.play();
        AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/tntBlast.mp3")).toExternalForm());
        buzzer.play();
    }


    public int getRadius() {
        return this.radius;
    }

    public int getDamage() {
        return this.damage;
    }

    public boolean getIsBurst() {
        return this.isBurst;
    }

    public int getTimeToBurst() {
        return timeToBurst;
    }

    public void setBurst(boolean burst) {
        isBurst = burst;
    }

    @Override
    public boolean collide(Game_objects game_objects) {
        Hero h1 = (Hero) game_objects;
        AtomicBoolean collision = new AtomicBoolean(false);
        if (this.getImg().getBoundsInLocal().intersects(h1.getImg().getBoundsInLocal())) {
            if(!getIsBurst()) {
                activate(h1);
                setBurst(true);
                collision.set(true);
            }
        }
        return collision.get();
    }
}
