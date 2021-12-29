package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application implements Serializable {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        String path = "src/assets/openingBackground2.mp4";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.3);
        MediaView mediaView = new MediaView(mediaPlayer);
        Group root = new Group();
        root.getChildren().add(mediaView);
        root.getChildren().add(fxmlLoader.load());
        Scene scene = new Scene(root,800,600);
        MainMenuController mainMenuController = fxmlLoader.getController();
        mainMenuController.initData(root, mediaPlayer, new Player(0));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Will Hero");
        primaryStage.show();
        Stage primaryStage1 = new Stage();


        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        String path1 = "src/assets/openingBackground2.mp4";
        Media media1 = new Media(new File(path1).toURI().toString());
        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
        mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer1.setAutoPlay(true);
        mediaPlayer1.setVolume(0.3);
        MediaView mediaView1 = new MediaView(mediaPlayer1);
        Group root1 = new Group();
        root1.getChildren().add(mediaView1);
        root1.getChildren().add(fxmlLoader1.load());
        Scene scene1 = new Scene(root1,800,600);
        MainMenuController mainMenuController1 = fxmlLoader1.getController();
        mainMenuController1.initData(root1, mediaPlayer1, new Player(1));
        primaryStage1.setScene(scene1);
        primaryStage1.setTitle("Will Hero");
        primaryStage1.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
