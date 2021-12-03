package sample;

import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseMenuController {
    private Stage stage;
    private Scene scene;
    private Group root;
    private Timeline time;
    private AnchorPane pauseMenu;
    private MediaPlayer mediaPlayer;
    public void initData(Group root, Timeline time, AnchorPane menu){
        this.root = root;
        this.time = time;
        this.pauseMenu = menu;
        this.mediaPlayer = null;
    }
    public void initData(Group root, MediaPlayer mediaPlayer, AnchorPane menu){
        this.root = root;
        this.mediaPlayer = mediaPlayer;
        this.pauseMenu = menu;
        this.time = null;
    }

    public void start(MouseEvent mouseEvent) {
        root.getChildren().remove(pauseMenu);
        if(time != null)time.play();
        else mediaPlayer.play();
    }

    public void home(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        this.root.getChildren().remove(pauseMenu);
        stage.show();
    }
}
