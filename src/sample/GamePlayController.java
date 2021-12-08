package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GamePlayController {
    @FXML
    private ImageView setting;
    private Group root;
    private MediaPlayer mediaPlayer;
    private Chests chest;
    private Timeline time;
    private Hero hero;
    private ArrayList<Island> islands;
    private ArrayList<Orc> orcs;

    public void initData(Group root, Hero hero,ArrayList<Island> islands,Obstacle tnt, ArrayList<Orc> orcs, Chests chest,MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
        this.root = root;
        this.hero = hero;
        this.islands = islands;
        this.orcs = orcs;
        this.chest = chest;

        for(Island island : islands){
            island.makeImage(root);
        }
        hero.makeImage(root);
        tnt.makeImage(root);
        chest.makeImage(root);
        ((TNT)tnt).tntPlay();
        chest.chestAnimation();
        for(Orc orc : orcs){
            orc.makeImage(root);
        }
//        root.getChildren().add(new Button());
//        root.getChildren().get(root.getChildren().size()-1).setOnMouseClicked(mouseEvent -> {
//            hero.getImg().setLayoutX(hero.getImg().getLayoutX()+20);
//            for(int i = 1; i<root.getChildren().size()-1; i++) {
//                if (root.getChildren().get(i) != hero.getImg())
//                    root.getChildren().get(i).setLayoutX(root.getChildren().get(i).getLayoutX() - 200);
//            }
//            if(hero.getImg().getLayoutX()>=160){
//                hero.getImg().setLayoutX(100);
//                for(int i = 1; i<root.getChildren().size()-1; i++) {
//                    if (root.getChildren().get(i) != hero.getImg())
//                        root.getChildren().get(i).setLayoutX(root.getChildren().get(i).getLayoutX() + 600);
//                }
//            }
//        });
        KeyFrame heroFrame = new KeyFrame(Duration.millis(11), e->{
            hero.jump();
        });
        KeyFrame orcFrame = new KeyFrame(Duration.millis(10), e->{
            moveOrc();
        });
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            chest.getImg().setY(islands.get(1).getImg().getY()+210);
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
        if(orcs.get(0).getImg().getY()-orcs.get(0).getSpeedy() > islands.get(1).getImg().getY()+210){
            orcs.get(0).getImg().setY(islands.get(1).getImg().getY()+210);
            double speed = orcs.get(0).getSpeedy();
            orcs.get(0).setSpeedy(-speed);
        }else {
            orcs.get(0).getImg().setY(orcs.get(0).getImg().getY() - orcs.get(0).getSpeedy());
        }
        if(orcs.get(0).getImg().getY()<=islands.get(1).getImg().getY()+100){
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
