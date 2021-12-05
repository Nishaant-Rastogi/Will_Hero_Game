package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GamePlayController {
    @FXML
    private ImageView setting;
    private Stage stage;
    private Scene scene;



    private Group root;



    private MediaPlayer mediaPlayer;


    private Timeline time;
    private Hero hero;
    private ArrayList<Island> islands;
    private ArrayList<Orc> orcs;
    public void initData(Group root, Hero hero,ArrayList<Island> islands,Obstacle tnt, ArrayList<Orc> orcs, MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
        this.root = root;
        this.hero = hero;
        this.islands = islands;
        this.orcs = orcs;
        for(Island island : islands){
            island.makeImage(root);
        }
        hero.makeImage(root);
        tnt.makeImage(root);
        for(Orc orc : orcs){
            orc.makeImage(root);
        }
        KeyFrame heroFrame = new KeyFrame(Duration.millis(11), e->{
            hero.jump();
        });
        KeyFrame orcFrame = new KeyFrame(Duration.millis(10), e->{
            moveOrc();
        });
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            islands.get(1).jump();
        });
        this.time = new Timeline(heroFrame,frame,orcFrame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
    }

    public void pause() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PauseMenu.fxml"));

        AnchorPane pauseMenu = fxmlLoader.load();
        pauseMenu.setLayoutX(270);
        pauseMenu.setLayoutY(10);
        root.getChildren().add(pauseMenu);
        time.pause();
        PauseMenuController pauseMenuController = fxmlLoader.getController();
        if(this.mediaPlayer.isMute()) {
            pauseMenuController.getMusic().setImage(new Image(new File("src/assets/MusicButtonClose.png").toURI().toString()));
        }
        pauseMenuController.initData(this, pauseMenu);
    }
    public void moveOrc(){
        if(orcs.get(0).img.getY()-orcs.get(0).getSpeedy() > islands.get(1).img.getY()+210){
            orcs.get(0).img.setY(islands.get(1).img.getY()+210);
            double speed = orcs.get(0).getSpeedy();
            orcs.get(0).setSpeedy(-speed);
        }else {
            orcs.get(0).img.setY(orcs.get(0).img.getY() - orcs.get(0).getSpeedy());
        }
        if(orcs.get(0).img.getY()<=islands.get(1).img.getY()+100){
            double speed = orcs.get(0).getSpeedy();
            orcs.get(0).setSpeedy(-speed);
        }
    }
    public Group getRoot() {
        return root;
    }
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public Timeline getTime() {
        return time;
    }
    public void shadowEffectSetting(MouseEvent mouseEvent) {
        setting.setEffect(new DropShadow(20, Color.BLACK));
    }
    public void removeShadowEffectSetting(MouseEvent mouseEvent) {
        setting.setEffect(null);
    }
}
