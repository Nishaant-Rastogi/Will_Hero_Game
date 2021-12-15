package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public abstract class Chests extends Game_objects implements Jumpable{
    private boolean isOpen;
    private List<Image> chestAnimation;
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

    @Override
    public void collide(Game_objects hero){
        //System.out.println("hello");
        if(this.getImg().getBoundsInLocal().intersects(hero.getImg().getBoundsInLocal()) && !this.isOpen) {
            chestAnimation();
            this.isOpen=true;
            //System.out.println("This is the animation");
        }
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
