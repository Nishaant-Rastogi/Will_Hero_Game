
package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenuController {

    private Stage stage;
    private Scene scene;
    private Group root;

    @FXML
    private ImageView play;
    @FXML
    private ImageView music;
    @FXML
    private ImageView load;
    @FXML
    private ImageView settings;
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
            javafx.scene.image.Image music = new javafx.scene.image.Image(new File("src/assets/MusicButton.png").toURI().toString());
            this.music.setImage(music);
            mediaPlayer.setMute(false);
        }else {
            javafx.scene.image.Image musicOn = new Image(new File("src/assets/MusicButtonClose.png").toURI().toString());
            this.music.setImage(musicOn);
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
        String smallIslandPath = "src/assets/island_2.png";
        Island island = new Island(90, 180, 0, 0.5, islandPath, 350, 350, -1);
        Island smallIsland = new Island(500,100,0,0.5,smallIslandPath,180,400,-1);
        ArrayList<Island> islands = new ArrayList<>();
        ArrayList<Orc> orcs = new ArrayList<>();
        islands.add(island);islands.add(smallIsland);
        Hero hero = new Hero(120,250,0,2,heroPath,80,80);
        Orc greenOrc = new Normal_G_Orc(520,250,0,2.5,greenOrcPath,80,80);
        orcs.add(greenOrc);
        Obstacle tnt = new TNT(320,240,0,1,tntPath,80,80);

        root.getChildren().add(fxmlLoader.load());
        GamePlayController gamePlayController = fxmlLoader.getController();
        gamePlayController.initData(root, hero, islands, tnt, orcs, mediaPlayer);

        scene = new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();
    }

    public ImageView getMusic() {
        return music;
    }

    public void shadowEffectPlay(MouseEvent mouseEvent) {
        play.setEffect(new DropShadow(20, Color.BLACK));
    }
    public void shadowEffectMusic(MouseEvent mouseEvent) {
        music.setEffect(new DropShadow(20, Color.BLACK));
    }
    public void shadowEffectLoad(MouseEvent mouseEvent) {
        load.setEffect(new DropShadow(20, Color.BLACK));
    }
    public void shadowEffectSetting(MouseEvent mouseEvent) {
        settings.setEffect(new DropShadow(20, Color.BLACK));
    }
    public void removeShadowEffectPlay(MouseEvent mouseEvent) {
        play.setEffect(null);
    }
    public void removeShadowEffectMusic(MouseEvent mouseEvent) {
        music.setEffect(null);
    }
    public void removeShadowEffectLoad(MouseEvent mouseEvent) {
        load.setEffect(null);
    }
    public void removeShadowEffectSetting(MouseEvent mouseEvent) {
        settings.setEffect(null);
    }
}