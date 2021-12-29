package sample;

import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
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

import java.io.*;
import java.util.*;


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
    private Button inputButton;
    public void initData(GamePlayController gamePlayController, AnchorPane menu, Button inputButton){
        this.gamePlayController = gamePlayController;
        this.root = gamePlayController.getRoot();
        this.time = gamePlayController.getTime();
        this.pauseMenu = menu;
        this.mediaPlayer = gamePlayController.getMediaPlayer();
        this.inputButton=inputButton;
    }
    public void initData(MainMenuController mainMenuController, AnchorPane menu){
        this.mainMenuController = mainMenuController;
        this.root = mainMenuController.getRoot();
        this.mediaPlayer = mainMenuController.getMediaPlayer();
        this.pauseMenu = menu;
    }

    public void start(MouseEvent mouseEvent) {
        root.getChildren().remove(pauseMenu);
        this.inputButton.setDisable(false);
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
        root.getChildren().removeAll();
        Group root = new Group();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GamePlay.fxml"));
        FXMLLoader sky = new FXMLLoader(Main.class.getResource("sky.fxml"));
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
        for(int i=0;i<4;i++) {
            if(i>0)islands.add(new Island(50+x, 200, 0, 0, isLand1 - 1, 320, 350, 70,gameObjectGenerator(50+x+100, 180,320)));
            else islands.add(new Island(50+x, 200, 0, 0, isLand1 - 1, 320, 350, 70,null));
            islands.add(new Island(400+x, 80, 0, 0.5, isLand2 - 1, 400, 520, 210,gameObjectGenerator(400+x+180, 100,400)));
            islands.add(new Island(900+x, 100, 0, 0.2, isLand3 - 1, 280, 420, 165,gameObjectGenerator(900+x+50, 100,400)));
            islands.add(new Island(1300+x, 100, 0, 0, isLand4 - 1, 150, 400, 210,gameObjectGenerator(1300+x+20, 100,150)));
            islands.add(new Island(1650+x, 20, 0, 0, isLand5 - 1, 440, 520, 265,gameObjectGenerator(1650+x+150, 100,440)));
            islands.add(new Island(2250+x, 100, 0, 0.5, isLand6 - 1, 380, 500, 177,gameObjectGenerator(2250+x+100, 150,380)));
            islands.add(new Island(2750+x, 200, 0, 0.3, isLand7 - 1, 320, 350, 70,gameObjectGenerator(2750+x+100, 180,320)));
            islands.add(new Island(3200+x, 100, 0, 0, isLand8 - 1, 150, 400, 210,gameObjectGenerator(3200+x+20, 100,150)));
            if(i == 3){
                islands.add(new Island(3420+x, 100, 0, 0, isLand6 - 1, 380, 500, 177,null));
                islands.add(new Island(3700+x, 65, 0, 0, isLand2 - 1, 400, 520, 210,null));
                x += 800;
            }
            islands.add(new Island(3420+x, 100, 0, 0.5, isLand9 - 1, 380, 480, 177,gameObjectGenerator(3420+x+120, 100,380)));
            islands.add(new Island(3900+x, 20, 0, 0, isLand10 - 1, 440, 520, 265,gameObjectGenerator(3900+x+150, 100,440)));
            x+=4400;
        }
        Hero hero = new Hero(100,250,0,2,55,65, islands.get(0));
        root.getChildren().add(fxmlLoader.load());
        root.getChildren().add(sky.load());
        GamePlayController gamePlayController = fxmlLoader.getController();
        gamePlayController.initData(root, hero, islands, mediaPlayer);
        scene = new Scene(root,1000,600);
        stage.setScene(scene);
        stage.show();
    }
    public Game_objects gameObjectGenerator(double x, double y, int width){
        Random rand = new Random();
        int randInteger = 1 + rand.nextInt(3);
        if(randInteger == 1){
            return new TNT(x,y,0,0,70,70);
        }else if(randInteger == 2){
            Random randChest = new Random();
            int randChestType = 1 + randChest.nextInt(2);
            if(randChestType == 1) return new Weapon_chest(x,y,0,100,100,70);
            else return new Coin_chest(x,y,0,0,110,90);
        }
        return null;
    }
    public void home(MouseEvent mouseEvent) throws IOException {
        this.mediaPlayer.dispose();
        root.getChildren().removeAll();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
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
        mainMenuController.initData(root, mediaPlayer);
        if(this.mediaPlayer.isMute()){
            mainMenuController.getMusic().setImage(new Image(new File("src/assets/MusicButtonClose.png").toURI().toString()));
            mediaPlayer.setMute(true);
        }
        stage.setScene(scene);
        stage.setTitle("Will Hero");
        stage.show();
    }
    public void save(MouseEvent mouseEvent) throws IOException {
        String fileName = "src/savedGames/save.txt";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for(int i = 0; i<gamePlayController.getGameObjects().size(); i++){
                Game_objects gameObject = gamePlayController.getGameObjects().get(i);
                System.out.println(gameObject.getPath());
                if(gameObject instanceof TNT){
                    if (((TNT) gameObject).getIsBurst()){
                        continue;
                    }
                }
                gameObject.setPosition(new double[]{gameObject.getImg().getX(), gameObject.getImg().getY()});
            }
            out.writeObject(gamePlayController);
        }
    }
    public ImageView getMusic() {
        return music;
    }
}
