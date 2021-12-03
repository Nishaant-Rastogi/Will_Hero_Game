
package sample;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenuController {

    private Stage stage;
    private Scene scene;
    private Group root;
    private MediaPlayer mediaPlayer;
    public void initData(Group root, MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
        this.root = root;
    }
    public void pauseMenu(javafx.scene.input.MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PauseMenu.fxml"));
        AnchorPane pauseMenu = fxmlLoader.load();
        pauseMenu.setLayoutX(270);
        pauseMenu.setLayoutY(50);
        root.getChildren().add(pauseMenu);
        mediaPlayer.pause();
        PauseMenuController pauseMenuController = fxmlLoader.getController();
        pauseMenuController.initData(root, mediaPlayer, pauseMenu);
    }
    public void musicButton(javafx.scene.input.MouseEvent event) throws IOException{
        if(mediaPlayer.isMute()) {
            mediaPlayer.setMute(false);
        }else {
            mediaPlayer.setMute(true);
        }
    }
    public void reviveMenu(javafx.scene.input.MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReviveMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gamePlay(javafx.scene.input.MouseEvent event) throws IOException {
        Group root = new Group();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GamePlay.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        //GameObjects Added
        String islandPath = "src/assets/island_1.png";
        String heroPath = "src/assets/Gladiator.png";
        String greenOrcPath = "src/assets/Green_Orc.png";
        String tntPath = "src/assets/TNT.png";
        Island island = new Island(90, 180, 0, 1, islandPath, 350, 350, -1);
        Hero hero = new Hero(120,250,0,2,heroPath,80,80);
        Orc greenOrc = new Normal_G_Orc(240,240,0,2.5,greenOrcPath,80,80);
        Obstacle tnt = new TNT(320,230,0,1,tntPath,90,90);
        root.getChildren().add(fxmlLoader.load());
        GamePlayController gamePlayController = fxmlLoader.getController();
        gamePlayController.initData(root, hero, island, tnt,greenOrc);

        scene = new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();

    }
}