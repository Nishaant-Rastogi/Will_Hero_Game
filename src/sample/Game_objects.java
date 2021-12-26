package sample;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;


public abstract class Game_objects implements Serializable {
    private final double[] position;
    private double speedX;
    private double speedY;
    private String path;
    transient private ImageView img;
    private int width;
    private int height;

    Game_objects(double x, double y, double sx, double sy, String path, int width, int height){
        position = new double[2];
        position[0]=x;
        position[1]=y;
        this.path=path;
        speedX =sx;
        speedY =sy;
        this.width=width;
        this.height=height;
    }
    public void setImg(ImageView img) {
        this.img = img;
    }

    public void makeImage(Group pane){
        img = new ImageView(new File(path).toURI().toString());
        img.setX(position[0]);
        img.setY(position[1]);
        img.setFitWidth(width);
        img.setFitHeight(height);
        pane.getChildren().add(img);
    }
    public abstract boolean collide(Game_objects game_objects);
    public double[] getposition(){
        return this.position;
    }
    public void setPosition(double[] a){
        this.position[0]=a[0];
        this.position[1]=a[1];

    }
    public ImageView getImg() {
        return img;
    }

    public String getPath(){
        return path;
    }

    public int getWidth() {
        return width;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double[] getPosition() {
        return position;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void display(){

    }

    public void disappear(){

    }
}
