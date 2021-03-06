package sample;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Chests extends Game_objects implements Jumpable{
    private boolean isOpen;
    private transient List<Image> chestAnimation;
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
                AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/chestOpen.mp3")).toExternalForm());
                buzzer.play();
                setIsOpen();
                return true;
            }
        }
        return false;
    }
    public void chestAnimation(){
        Chests chest= this;
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(200));}
            @Override
            protected void interpolate(double fraction) {
                if(chestAnimation==null){
                    chestAnimation = new ArrayList<>();
                    if(chest instanceof Weapon_chest){
                        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_1.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_2.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_3.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_4.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_5.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_6.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_7.png").toURI().toString()));
                    }
                    else{
                        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest2.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest3.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest4.png").toURI().toString()));
                        getChestAnimation().add(new Image(new File("src/assets/Coin_Chest5.png").toURI().toString()));
                    }
                }
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
