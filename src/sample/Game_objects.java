package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.Serializable;


public abstract class Game_objects {
    double position[];
    double speedx;
    double speedy;
    protected String path;
    transient protected ImageView img;
    protected int width;
    protected int height;

    public Game_objects() {
        position = new double[2];
        speedx=0;
        speedy=0;
    }

    Game_objects(double x, double y, double sx, double sy, String path, int width, int height){
        position = new double[2];
        position[0]=x;
        position[1]=y;
        this.path=path;
        speedx=sx;
        speedy=sy;
        this.width=width;
        this.height=height;
    }
    public void makeImage(Group pane){
        img = new ImageView(new File(path).toURI().toString());
        img.setX(position[0]);
        img.setY(position[1]);
        pane.getChildren().add(img);
    }
    public abstract void collide(Game_objects game_objects);
    public double[] getposition(){
        return this.position;
    }
    public void setPosition(double[] a){
        this.position[0]=a[0];
        this.position[1]=a[1];

    }

    public double getSpeedx() {
        return speedx;
    }

    public double[] getPosition() {
        return position;
    }

    public void setSpeedx(double speedx) {
        this.speedx = speedx;
    }

    public double getSpeedy() {
        return speedy;
    }

    public void setSpeedy(double speedy) {
        this.speedy = speedy;
    }

    public void display(){

    }

    public void disappear(){

    }
}
