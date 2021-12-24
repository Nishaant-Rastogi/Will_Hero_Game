package sample;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Orc extends Game_objects implements Jumpable{
    private int damage;
    private int health=100;
    private boolean dead;
    private Island currIsland;
    private ArrayList<Coin> coins;
    private List<Image> orcDeathAnimation;
    public Orc(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
        dead=false;
        orcDeathAnimation= new ArrayList<>();
        orcDeathAnimation.add(new Image(new File(path).toURI().toString()));
        orcDeathAnimation.add(new Image(new File("src/assets/Dead.png").toURI().toString()));
        orcDeathAnimation.add(new Image(new File("src/assets/Dead.png").toURI().toString()));
        orcDeathAnimation.add(new Image(new File("src/assets/Dead.png").toURI().toString()));
        orcDeathAnimation.add(new Image(new File("src/assets/Dead.png").toURI().toString()));
        orcDeathAnimation.add(new Image(new File("src/assets/Dead.png").toURI().toString()));
        orcDeathAnimation.add(new javafx.scene.image.Image(new File("src/assets/blank.png").toURI().toString()));
        coins = new ArrayList<>();
        for (int i = 0; i<5; i++){
            coins.add(new Coin(0,0,0,0,10,10));
        }
    }

    public void setCurrIsland(Island currIsland) {
        this.currIsland = currIsland;
    }

    public Island getCurrIsland() {
        return currIsland;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void orcToOrcCollision(int index, Hero h1){
        if(index >= currIsland.getOrcs().size()) return;
        if(index + 1 < currIsland.getOrcs().size()){
            if (this.getImg().getBoundsInLocal().intersects(currIsland.getOrcs().get(index + 1).getImg().getBoundsInLocal())) {
                currIsland.getOrcs().get(index + 1).getImg().setX(currIsland.getOrcs().get(index + 1).getImg().getX()+20);
                orcToOrcCollision(index + 1, h1);
            }
        }
        if ((currIsland.getImg().getX() > this.getImg().getX()+70) || (currIsland.getImg().getX() + currIsland.getWidth() <= this.getImg().getX())) {
            System.out.println("Fall");
            //need to slow down the animation
            TranslateTransition animation = new TranslateTransition(Duration.seconds(2),this.getImg());
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setFromY(this.getImg().getY());
            animation.setToY(530);
            animation.setAutoReverse(false);
            animation.play();
            dead=true;
            h1.getCoinCase().addAll(this.coins);
            orcDeathAnimation();
            //fall
        }
    }
    @Override
    public boolean collide(Game_objects game_objects){
        if(game_objects instanceof Hero) {
            Hero h1 = (Hero) game_objects;
            if (h1.getWeapon() != null) {
                if (this.getImg().getBoundsInLocal().intersects(game_objects.getImg().getBoundsInLocal())) {
                    if ((this.getImg().getX() <= h1.getImg().getX()) || (this.getImg().getX() + 70 >= h1.getImg().getX() + 55)) {
                        if ((this.getImg().getX() <= h1.getImg().getX() + 55)) {
                            if (this.getImg().getY() + 60 <= h1.getImg().getY()) {
                                return true;
                            }
                        }
                    }
                }
                if (this.getImg().getBoundsInLocal().intersects(h1.getWeapon().getImg().getBoundsInLocal())) {
                    if ((this.getImg().getX() <= h1.getWeapon().getImg().getX()) || (this.getImg().getX() + 70 >= h1.getWeapon().getImg().getX() + 55)) {
                        //System.out.println((this.getImg().getY() +60)+" "+h1.getImg().getY()+" "+h1.getWeapon().getImg().getY());
                            dead=true;
                            h1.getCoinCase().addAll(this.coins);
                            orcDeathAnimation();
                        }
                    }
            } else {
                if (this.getImg().getBoundsInLocal().intersects(game_objects.getImg().getBoundsInLocal())) {
                    if ((this.getImg().getX() <= h1.getImg().getX()) || (this.getImg().getX() + 70 >= h1.getImg().getX() + 55)) {
                        if((this.getImg().getX() <= h1.getImg().getX() + 55)){
                            if (this.getImg().getY() + 60 <= h1.getImg().getY()) {
                                return true;
                            }
                            this.getImg().setX(this.getImg().getX()+20);
                            int index = 0;
                            for (int i = 0; i<currIsland.getOrcs().size(); i++){
                                if(this.equals(currIsland.getOrcs().get(i))){
                                    index = i;
                                    break;
                                }
                            }
                            orcToOrcCollision(index, h1);
                            return false;
                       }

                    }
                }

            }
        }
        return false;
    }
    @Override
    public void jump() {
        if(this instanceof Boss_orc) {
            if (this.getImg().getY() - this.getSpeedY() >= currIsland.getImg().getY() + currIsland.getBase() - 80) {
                this.getImg().setY(currIsland.getImg().getY() + currIsland.getBase() - 80);
                double speed = this.getSpeedY();
                this.setSpeedY(-speed);
            } else {
                this.getImg().setY(this.getImg().getY() - this.getSpeedY());
            }
            if (this.getImg().getY() <= currIsland.getImg().getY() + currIsland.getBase() - 200) {
                this.getImg().setY(currIsland.getImg().getY() + currIsland.getBase() - 200);
                double speed = this.getSpeedY();
                this.setSpeedY(-speed);
            }
        }else{
            if (this.getImg().getY() - this.getSpeedY() >= currIsland.getImg().getY() + currIsland.getBase()) {
                this.getImg().setY(currIsland.getImg().getY() + currIsland.getBase());
                double speed = this.getSpeedY();
                this.setSpeedY(-speed);
            } else {
                this.getImg().setY(this.getImg().getY() - this.getSpeedY());
            }
            if (this.getImg().getY() <= currIsland.getImg().getY() + currIsland.getBase() - 200) {
                this.getImg().setY(currIsland.getImg().getY() + currIsland.getBase() - 200);
                double speed = this.getSpeedY();
                this.setSpeedY(-speed);
            }
        }
    }
    public void die(){

    }
    public void kill(Hero h){
        //this needs to be changed
        System.exit(0);
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean b) {
        this.dead = b;
    }
    public void orcDeathAnimation(){
        Orc orc= this;
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(200));}
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(orcDeathAnimation.size()-1));
                orc.getImg().setImage(orcDeathAnimation.get(index));
            }
        };
        animation.play();
        AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/orcDie.mp3")).toExternalForm());
        buzzer.play();
        animation.setCycleCount(1);
    }

}
