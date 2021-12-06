package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public abstract class Chests extends Game_objects implements Jumpable{
    private boolean isOpen;
    protected List<Image> chestAnimation;
    public Chests(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
        chestAnimation= new ArrayList<>();
        this.isOpen= false;
    }
    public void setIsOpen(){
        this.isOpen=true;
    }
    public boolean getIsOpen(){
        return this.isOpen;

    }
    public void chestAnimation(){
        Chests chest= this;
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(1000));}
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(chestAnimation.size()-1));
                chest.img.setImage(chestAnimation.get(index));
            }
        };
        chest.img.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            animation.play();
        });

    }
    @Override
    public void jump(){

    }

}
