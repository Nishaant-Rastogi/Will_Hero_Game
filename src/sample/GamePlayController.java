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
    private ArrayList<Obstacle> obs;
    private ArrayList<Coin> coins;
    private Button inputButton;

    public void initData(Group root, Hero hero,ArrayList<Island> islands,MediaPlayer mediaPlayer,ArrayList<Coin> c) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GamePlay.fxml"));
        this.mediaPlayer = mediaPlayer;
        this.root = root;
        this.hero = hero;
        this.islands = islands;
        this.coins=c;
        final int[] cur = {0};
        //current island index;
        for(Island island : this.islands){
            island.makeImage(root);
            try {
                island.getObject().makeImage(root);
            }catch (NullPointerException ignored){}
        }
        int heroIsland = 0;
        for(Island island : this.islands){
            if(heroIsland == 0) {
                heroIsland++;
                continue;
            }
            island.setOrcs(generateOrcs(island));
            for(Orc orc: island.getOrcs()){
                orc.makeImage(root);
            }
        }
        for (Coin coin : coins) {
            coin.makeImage(root);
        }
        hero.makeImage(root);
        root.getChildren().add(root.getChildren().remove(0));
        inputButton = new Button();
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
                    ((ImageView)root.getChildren().get(i)).setX(((ImageView)root.getChildren().get(i)).getX() - 70);
            }
            for(int i = cur[0]; i<this.islands.size(); i++){

                if((this.islands.get(i).getImg().getX()+this.islands.get(i).getWidth()) >= 100) { //&& (islands.get(i).getImg().getX()-islands.get(i).getWidth()/2.0) >= 0) {
                    this.hero.setCurrIsland(this.islands.get(i));
                    if(this.islands.get(i).getImg().getX() > 100) {
                        System.out.println("Fall");
                        break;
                    }
                    cur[0] = i;
                    System.out.println(hero.getImg().getX()+" "+islands.get(i).getImg().getX());
                    break;
                }
            }
        });
        KeyFrame heroFrame = new KeyFrame(Duration.millis(11), e->{
            hero.jump();
        });
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            for (Island island : this.islands) {
                try {
                    if (island.getObject() instanceof Coin_chest)
                        island.getObject().getImg().setY(island.getImg().getY() + island.getBase() - 30);
                    else island.getObject().getImg().setY(island.getImg().getY() + island.getBase());
                } catch (NullPointerException ignored) {}
                if(island.getOrcs().size() > 0) {
                    for (Orc orc : island.getOrcs()) {
                        orc.setCurrIsland(island);
                        orc.jump();
                    }
                }
                island.jump();
            }

        });
        this.time = new Timeline(heroFrame,frame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();

    }
    public ArrayList<Orc> generateOrcs(Island island){
        ArrayList<Orc> orcs= new ArrayList<>();
        Random randomOrc = new Random();
        int maxNo=3; double x,y;
        Game_objects onIsland= island.getObject();

        for(int i=0;i<maxNo;i++){
            x=island.getImg().getX()+50;
            //y here was set randomly
            y=island.getImg().getY()+island.getBase();

            if(orcs.size() > 0 && island.getObject() != null) {
                if (x + 70 > orcs.get(orcs.size()-1).getPosition()[0]) {
                    x += Math.abs(x + 70 - orcs.get(orcs.size()-1).getPosition()[0]);
                }else if (orcs.get(orcs.size()-1).getPosition()[0] + 70 > x) {
                    x += Math.abs(orcs.get(orcs.size()-1).getPosition()[0] + 70 - x);
                }
                if (x + 140 > onIsland.getImg().getX()) {
                    x += Math.abs(x + 140 - onIsland.getImg().getX());
                }else if (onIsland.getImg().getX() + 140 > x) {
                    x += Math.abs(onIsland.getImg().getX() + 140 - x);
                }
            }else if(island.getObject() != null) {
                if (x + 140 > onIsland.getImg().getX()) {
                    x += Math.abs(x + 140 - onIsland.getImg().getX());
                }
                if (onIsland.getImg().getX() + 140 > x) {
                    x += Math.abs(onIsland.getImg().getX() + 140 - x);
                }
            }else{
                if(orcs.size() > 0) {
                    if (x + 70 > orcs.get(orcs.size() - 1).getPosition()[0]) {
                        x += Math.abs(x + 70 - orcs.get(orcs.size() - 1).getPosition()[0]);
                    } else if (orcs.get(orcs.size() - 1).getPosition()[0] + 70 > x) {
                        x += Math.abs(orcs.get(orcs.size() - 1).getPosition()[0] + 70 - x);
                    }
                }
            }
            if(x + 70 < island.getImg().getX()+island.getWidth()) {
                int orcChoice = 1 + randomOrc.nextInt(2);
                int speedChoice = 2 + randomOrc.nextInt(3);
                if (orcChoice == 1) orcs.add(new Normal_G_Orc(x, y, 0, speedChoice, 70, 70));
                else orcs.add(new Shield_R_Orc(x, y, 0, speedChoice, 70, 70));
            }
        }
        return orcs;
        //there may be no orcs
    }
    public void pause() throws IOException {
        inputButton.setDisable(true);
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
        pauseMenuController.initData(this, pauseMenu,inputButton);
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