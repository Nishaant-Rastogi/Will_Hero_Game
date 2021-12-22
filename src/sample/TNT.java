package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class TNT extends Obstacle {
    private int damage;
    private int radius;
    private int timeToBurst;
    private boolean isBurst;
    private boolean isActivate;
    private List<Image> animation;
    private Transition tntAnimation;
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
                if(index==18){
                    isActivate=true;
                }

            }
        };
//        System.out.println("TNT animation ");
//            animation.play();
        this.tntAnimation=animation;

    }
    public void activate(){

    }

    public void activate(Hero h1) {
        //hopefully works
        this.getposition()[0]=this.getImg().getX();
        this.getposition()[1]=this.getImg().getY();
        isBurst=true;
        AtomicBoolean returnVal= new AtomicBoolean(false);
        ArrayList<Orc> orcs = h1.getCurrIsland().getOrcs();
        tntPlay();
        this.tntAnimation.play();
        this.tntAnimation.setOnFinished(E->{
            for (int i=0;i<orcs.size();i++) {
                if (((this.getposition()[0] - (orcs.get(i).getImg().getX() + 70)) <= radius) || ((orcs.get(i).getImg().getX() - this.getposition()[0] + 70) <= radius)) {
                    orcs.get(i).orcDeathAnimation();
                    orcs.remove(i);
                    i--;
                }
            }
            if(((h1.getImg().getX()-(this.getposition()[0]+70))<=radius)||((this.getposition()[0]-h1.getImg().getX()-55)<=radius)){
                System.out.println("Die stupid hero");
                 h1.setAlive();
                //if hero dies
            }
        });



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
