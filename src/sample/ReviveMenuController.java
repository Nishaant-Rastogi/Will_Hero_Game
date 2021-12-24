package sample;

import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReviveMenuController {
    @FXML
    private Text score;
    @FXML
    private Text coins;
    private MediaPlayer mediaPlayer;
    private Stage stage;
    private Scene scene;
    private Timeline time;
    private Group root;
    private Hero hero;
    private AnchorPane reviveMenu;
    private Button inputButton;
    private AtomicBoolean fall;
    public void initData(GamePlayController gamePlayController, AnchorPane reviveMenu, Hero hero, Text score, Text coins){
        this.score.setText(score.getText());
        this.root = gamePlayController.getRoot();
        this.time = gamePlayController.getTime();
        this.coins.setText(coins.getText());
        //java acting weird here with this.coins=coins up here
        this.coins=coins;
        this.reviveMenu = reviveMenu;
        this.mediaPlayer = gamePlayController.getMediaPlayer();
        this.hero = hero;
        this.fall = gamePlayController.getFall();
        this.inputButton=gamePlayController.getInputButton();
    }
    public void respawn(Hero hero){
        for(int i = 0; i < hero.getCurrIsland().getOrcs().size(); i++){
            if((hero.getCurrIsland().getOrcs().get(i).getImg().getX() <= hero.getImg().getX() + 55) || (hero.getCurrIsland().getOrcs().get(i).getImg().getX() + 70 >= hero.getImg().getX())){
                hero.getCurrIsland().getOrcs().get(i).orcDeathAnimation();
                hero.getCurrIsland().getOrcs().remove(i);
                i--;
            }
        }
        AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/revive.mp3")).toExternalForm());
        buzzer.play();
    }
    public void revive(MouseEvent mouseEvent)throws IOException{
        Button revive = new Button();
        ImageView button = new ImageView(new Image(new File("src/assets/revive.png").toURI().toString()));
        button.setFitWidth(320);
        button.setFitHeight(150);
        revive.setGraphic(button);
        revive.setPrefWidth(300);
        revive.setPrefHeight(150);
        revive.setLayoutX(350);
        revive.setLayoutY(200);
        if(hero.getIsRevived()||hero.getCoinCase().size()<5) {
           reloadButton(mouseEvent);
        }
        root.getChildren().remove(root.getChildren().size()-1);
        root.getChildren().add(root.getChildren().size()-1,revive);
        revive.setOnMousePressed(event -> {
            inputButton.setDisable(false);
            if(!hero.getIsRevived()){
               for(int i=0;i<5;i++)
                   hero.getCoinCase().remove(0);
                hero.setRevived();
                hero.setAlive(true);
                hero.getImg().setX(hero.getCurrIsland().getImg().getX() + 20);
                respawn(hero);
                if(hero.getImg().getX()<=0){
                    hero.getImg().setX(hero.getCurrIsland().getImg().getX()+hero.getCurrIsland().getWidth()-55);
                    respawn(hero);
                }
                hero.getImg().setY(hero.getCurrIsland().getImg().getY() + hero.getCurrIsland().getBase());
                root.getChildren().remove(root.getChildren().size() - 2);
                coins.setText(Integer.toString(hero.getCoinCase().size()));
                fall.set(false);
                time.play();
            }

        });

    }
    public void mainMenu(MouseEvent event) throws IOException {
        this.mediaPlayer.dispose();
        root.getChildren().removeAll();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
        for(int i=0;i<3;i++) {
            if(i>0)islands.add(new Island(50+x, 200, 0, 0, isLand1 - 1, 320, 350, 70,gameObjectGenerator(50+x+100, 180,320)));
            else islands.add(new Island(50+x, 200, 0, 0, isLand1 - 1, 320, 350, 70,null));
            islands.add(new Island(400+x, 80, 0, 0.5, isLand2 - 1, 400, 520, 210,gameObjectGenerator(400+x+180, 100,400)));
            islands.add(new Island(900+x, 100, 0, 0.2, isLand3 - 1, 280, 420, 165,gameObjectGenerator(900+x+50, 100,400)));
            islands.add(new Island(1300+x, 100, 0, 0, isLand4 - 1, 150, 400, 210,gameObjectGenerator(1300+x+20, 100,150)));
            islands.add(new Island(1650+x, 20, 0, 0, isLand5 - 1, 440, 520, 265,gameObjectGenerator(1650+x+150, 100,440)));
            islands.add(new Island(2250+x, 100, 0, 0.5, isLand6 - 1, 380, 500, 177,gameObjectGenerator(2250+x+100, 150,380)));
            islands.add(new Island(2750+x, 200, 0, 0.3, isLand7 - 1, 320, 350, 70,gameObjectGenerator(2750+x+100, 180,320)));
            islands.add(new Island(3200+x, 100, 0, 0, isLand8 - 1, 150, 400, 210,gameObjectGenerator(3200+x+20, 100,150)));
            if(i == 1){
                islands.add(new Island(3420+x, 100, 0, 0, isLand6 - 1, 380, 500, 177,null));
                islands.add(new Island(3700+x, 65, 0, 0, isLand2 - 1, 400, 520, 210,null));
                x += 800;
            }
            islands.add(new Island(3420+x, 100, 0, 0.5, isLand9 - 1, 380, 480, 177,gameObjectGenerator(3420+x+120, 100,380)));
            islands.add(new Island(3900+x, 20, 0, 0, isLand10 - 1, 440, 520, 265,gameObjectGenerator(3900+x+150, 100,440)));
            x+=4400;
        }
        Hero hero = new Hero(100,250,0,2,55,65, islands.get(0));
        //ArrayList<Coin> coins=new ArrayList<>();
        x=0;
//        for(int i=0;i<3;i++){
//            coins.add(new Coin(340+x,160,0,0,30,30));
//            x+=40;
//        }
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
}
