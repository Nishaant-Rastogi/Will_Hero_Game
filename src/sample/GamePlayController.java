package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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
    private Parent root;
    private Timeline time;
    private Hero hero;
    private Circle ball;
    @FXML
    private AnchorPane gameRoot;

    public void initData(Group root, Hero hero, Island island){
        this.hero = hero;
        hero.makeImage(root);
        island.makeImage(root);
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{ moveHero(); });
        this.time = new Timeline(frame);
        time.setCycleCount(Timeline.INDEFINITE);
        moveHero();
        time.play();
    }

    public void pauseMenu(javafx.scene.input.MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PauseMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void pause(){
        time.pause();
    }
    public void moveHero(){
        hero.img.setY(hero.img.getY()-hero.getSpeedy());
        if(hero.img.getY()>=250 || hero.img.getY()<=100){
            double speed = hero.getSpeedy();
            hero.setSpeedy(-speed);
        }
    }

}
