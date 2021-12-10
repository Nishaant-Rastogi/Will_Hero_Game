package sample;

import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.ArrayList;

public class PauseMenuController {
    @FXML
    private ImageView music;
    private Stage stage;
    private Scene scene;
    private Group root;
    private Timeline time;
    private AnchorPane pauseMenu;
    private MediaPlayer mediaPlayer;
    private MainMenuController mainMenuController;
    private GamePlayController gamePlayController;
    public void initData(GamePlayController gamePlayController, AnchorPane menu){
        this.gamePlayController = gamePlayController;
        this.root = gamePlayController.getRoot();
        this.time = gamePlayController.getTime();
        this.pauseMenu = menu;
        this.mediaPlayer = gamePlayController.getMediaPlayer();
    }
    public void initData(MainMenuController mainMenuController, AnchorPane menu){
        this.mainMenuController = mainMenuController;
        this.root = mainMenuController.getRoot();
        this.mediaPlayer = mainMenuController.getMediaPlayer();
        this.pauseMenu = menu;
    }

    public void start(MouseEvent mouseEvent) {
        root.getChildren().remove(pauseMenu);
        if(time != null) {
            time.play();
        }else {
            mediaPlayer.play();
            if(this.mediaPlayer.isMute()){
                mainMenuController.getMusic().setImage(new Image(new File("src/assets/MusicButtonClose.png").toURI().toString()));
                mediaPlayer.setMute(true);
            }else{
                mainMenuController.getMusic().setImage(new Image(new File("src/assets/MusicButton.png").toURI().toString()));
                mediaPlayer.setMute(false);
            }
        }
    }
    public void musicButton(MouseEvent event) throws IOException{
        if(mediaPlayer.isMute()) {
            Image music = new Image(new File("src/assets/MusicButton.png").toURI().toString());
            this.music.setImage(music);
            mediaPlayer.setMute(false);
        }else {
            Image musicOn = new Image(new File("src/assets/MusicButtonClose.png").toURI().toString());
            this.music.setImage(musicOn);
            mediaPlayer.setMute(true);
        }
    }
    public void reloadButton(MouseEvent mouseEvent) throws IOException{
        Group root = new Group();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GamePlay.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        //GameObjects Added
        int isLand1 = 1;
        int isLand2 =4;
        int  isLand3 =3;
        int  isLand4 =2;
        int isLand5 =5;
        int  isLand6 =6;
        int  isLand7 =1;
        int isLand8 =2;
        int isLand9 =6;
        int isLand10 =5;
        int x=0;
        ArrayList<Island> islands = new ArrayList<>();
        //to change loop counter
        for(int i=0;i<8;i++) {
            islands.add(new Island(50+x, 180, 0, 0, isLand1 - 1, 320, 350, -1));
            islands.add(new Island(400+x, 100, 0, 0.5, isLand2 - 1, 400, 520, -1));
            islands.add(new Island(900+x, 100, 0, 0.2, isLand3 - 1, 280, 420, -1));
            islands.add(new Island(1300+x, 100, 0, 0, isLand4 - 1, 150, 400, -1));
            islands.add(new Island(1650+x, 100, 0, 0, isLand5 - 1, 440, 520, -1));
            islands.add(new Island(2250+x, 150, 0, 0.5, isLand6 - 1, 380, 500, -1));
            islands.add(new Island(2750+x, 180, 0, 0.3, isLand7 - 1, 320, 350, -1));
            islands.add(new Island(3200+x, 100, 0, 0, isLand8 - 1, 150, 400, -1));
            islands.add(new Island(3420+x, 100, 0, 0.5, isLand9 - 1, 380, 480, -1));
            islands.add(new Island(3900+x, 100, 0, 0, isLand10 - 1, 440, 520, -1));
            x+=4400;
        }
        ArrayList<Orc> orcs = new ArrayList<>();
        Hero hero = new Hero(100,250,0,2,70,70);
        Orc greenOrc = new Normal_G_Orc(450,250,0,2.5,70,70);
        orcs.add(greenOrc);
        Obstacle tnt = new TNT(280,250,0,1,70,70);
        Weapon_chest chest = new Weapon_chest(580,100,0,0,100,70);

        root.getChildren().add(fxmlLoader.load());
        GamePlayController gamePlayController = fxmlLoader.getController();
        gamePlayController.initData(root, hero, islands, tnt, orcs, chest, mediaPlayer);
        scene = new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();
    }
    public void home(MouseEvent mouseEvent) throws IOException {
        this.mediaPlayer.dispose();
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
        if(this.mediaPlayer.isMute()){
            mainMenuController.getMusic().setImage(new Image(new File("src/assets/MusicButtonClose.png").toURI().toString()));
            mediaPlayer.setMute(true);
        }
        stage.setScene(scene);
        stage.setTitle("Will Hero");
        stage.show();
    }

    public ImageView getMusic() {
        return music;
    }
}
