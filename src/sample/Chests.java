package sample;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public abstract class Chests extends Game_objects implements Jumpable{
    private boolean isOpen;
    private List<Image> chestAnimation;
    public Chests(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
        chestAnimation= new ArrayList<>();
        this.isOpen= true;
    }
    public void setIsOpen(){
        this.isOpen=false;
    }
    public boolean getIsOpen(){
        return this.isOpen;

    }

    @Override
    public boolean collide(Game_objects game_objects){
        if(this.getImg().getBoundsInLocal().intersects(game_objects.getImg().getBoundsInLocal())) {
            if(getIsOpen()) {
                chestAnimation();
                setIsOpen();
                return true;
            }
        }
        return false;
    }
    public void chestAnimation(){
        Chests chest= this;
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(500));}
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(chestAnimation.size()-1));
                chest.getImg().setImage(chestAnimation.get(index));
            }
        };
        animation.play();
        animation.setCycleCount(1);
    }

    public List<Image> getChestAnimation() {
        return chestAnimation;
    }

    public void setChestAnimation(List<Image> chestAnimation) {
        this.chestAnimation = chestAnimation;
    }

    @Override
    public void jump(){

    }

}
