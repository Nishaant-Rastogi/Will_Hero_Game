package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.*;

public class GamePlayController {
    private Stage stage;
    private Scene scene;
    private Group root;
    private Timeline time;
    private Hero hero;

    public void initData(Group root, Hero hero, Island island, Obstacle tnt, Orc greenOrc){
        this.root = root;
        this.hero = hero;
        hero.makeImage(root);
        island.makeImage(root);
        tnt.makeImage(root);
        greenOrc.makeImage(root);
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{ moveHero(); });
        this.time = new Timeline(frame);
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
        pauseMenuController.initData(root, time, pauseMenu);
    }
    public void moveHero(){
        hero.img.setY(hero.img.getY()-hero.getSpeedy());
        if(hero.img.getY()>=250 || hero.img.getY()<=150){
            double speed = hero.getSpeedy();
            hero.setSpeedy(-speed);
        }
    }
}
