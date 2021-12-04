package sample;

import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PauseMenuController {
    private Stage stage;
    private Scene scene;
    private Group root;
    private Timeline time;
    private AnchorPane pauseMenu;
    private MediaPlayer mediaPlayer;
    public void initData(Group root, Timeline time, MediaPlayer mediaPlayer, AnchorPane menu){
        this.root = root;
        this.time = time;
        this.pauseMenu = menu;
        this.mediaPlayer = mediaPlayer;
    }
    public void initData(Group root, MediaPlayer mediaPlayer, AnchorPane menu){
        this.root = root;
        this.mediaPlayer = mediaPlayer;
        this.pauseMenu = menu;
    }

    public void start(MouseEvent mouseEvent) {
        root.getChildren().remove(pauseMenu);
        if(time != null)time.play();
        else mediaPlayer.play();
    }

    public void home(MouseEvent mouseEvent) throws IOException {
        this.mediaPlayer.setMute(true);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        String path = "src/assets/openingBackground2.mp4";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        Group root = new Group();
        root.getChildren().add(mediaView);
        root.getChildren().add(fxmlLoader.load());
        Scene scene = new Scene(root,800,600);
        MainMenuController mainMenuController = fxmlLoader.getController();
        mainMenuController.initData(root, mediaPlayer);
        stage.setScene(scene);
        stage.setTitle("Will Hero");
        stage.show();
    }
}
