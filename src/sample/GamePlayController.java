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
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GamePlayController {
    @FXML
    private ImageView setting;
    @FXML
    private Text score;
    private Group root;
    private MediaPlayer mediaPlayer;
    private ArrayList<Chests> chest;
    private Timeline time;
    private Hero hero;
    private ArrayList<Island> islands;
    private ArrayList<Orc> orcs;
    private ArrayList<Obstacle> obs;
    private ArrayList<Coin> coins;

    public void initData(Group root, Hero hero,ArrayList<Island> islands,ArrayList<Orc> orcs,MediaPlayer mediaPlayer,ArrayList<Coin> c) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GamePlay.fxml"));
        this.mediaPlayer = mediaPlayer;
        this.root = root;
        this.hero = hero;
        this.islands = islands;
        this.orcs = orcs;
        this.coins=c;

        for(Island island : islands){
            island.makeImage(root);
            try {
                island.getObject().makeImage(root);
            }catch (NullPointerException ignored){}
        }
        for (Coin coin : coins) {
            coin.makeImage(root);
        }
        for(Orc orc : orcs){
            orc.makeImage(root);
        }
        hero.makeImage(root);
        root.getChildren().add(root.getChildren().remove(0));
        Button inputButton = new Button();
        inputButton.setStyle("-fx-background-color: transparent;");
        inputButton.setLayoutY(100);
        inputButton.setPrefWidth(800);inputButton.setPrefHeight(400);
        root.getChildren().add(inputButton);
        //see if hold can give a power up
        root.getChildren().get(root.getChildren().size()-1).setOnMouseClicked(mouseEvent -> {
            hero.getMove().play();
            score.setText(Integer.toString(Integer.parseInt(score.getText())+1));
            for(int i = 1; i<root.getChildren().size()-2; i++) {
                if (root.getChildren().get(i) != hero.getImg())
                    ((ImageView)root.getChildren().get(i)).setX(((ImageView)root.getChildren().get(i)).getX() - 200);
            }
            if(hero.getImg().getX()>=200){
                hero.getImg().setX(100);
                for(int i = 1; i<root.getChildren().size()-1; i++) {
                    if (root.getChildren().get(i) != hero.getImg())
                        ((ImageView)root.getChildren().get(i)).setX(((ImageView)root.getChildren().get(i)).getX() + 100);
                }
            }
        });
        KeyFrame heroFrame = new KeyFrame(Duration.millis(11), e->{
            hero.jump();
        });
        KeyFrame orcFrame = new KeyFrame(Duration.millis(10), e->{
            moveOrc();
        });
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(70, 210, 165, 210, 265, 177, 70, 210, 177, 265));
            for(int i=0;i< islands.size();i++){
                islands.get(i).jump();
                try{
                    islands.get(i).getObject().getImg().setY(islands.get(i).getImg().getY()+arr.get((i%10)));
                }catch (NullPointerException ignored){}
            }

        });
        this.time = new Timeline(heroFrame,frame,orcFrame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();

    }
    public void pause() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PauseMenu.fxml"));

        AnchorPane pauseMenu = fxmlLoader.load();
        pauseMenu.setLayoutX(380);
        pauseMenu.setLayoutY(6);
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