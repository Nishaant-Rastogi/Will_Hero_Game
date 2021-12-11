
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
import java.util.List;
import java.util.Random;

import static java.lang.System.exit;

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
    public void exitGame(MouseEvent event) throws IOException {
        exit(0);
    }
    public void musicButton(MouseEvent event) throws IOException{
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
    public void reviveMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReviveMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gamePlay(MouseEvent event) throws IOException {
        Group root = new Group();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GamePlay.fxml"));
        FXMLLoader sky = new FXMLLoader(Main.class.getResource("sky.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

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
        for(int i=0;i<6;i++) {
            if(i>0)islands.add(new Island(50+x, 200, 0, 0, isLand1 - 1, 320, 350, 70,gameObjectGenerator(50+x+100, 180,320)));
            else islands.add(new Island(50+x, 200, 0, 0, isLand1 - 1, 320, 350, 70,null));
            islands.add(new Island(400+x, 80, 0, 0.5, isLand2 - 1, 400, 520, 210,gameObjectGenerator(400+x+180, 100,400)));
            islands.add(new Island(900+x, 100, 0, 0.2, isLand3 - 1, 280, 420, 165,gameObjectGenerator(900+x+50, 100,400)));
            islands.add(new Island(1300+x, 100, 0, 0, isLand4 - 1, 150, 400, 210,gameObjectGenerator(1300+x+20, 100,150)));
            islands.add(new Island(1650+x, 20, 0, 0, isLand5 - 1, 440, 520, 265,gameObjectGenerator(1650+x+150, 100,440)));
            islands.add(new Island(2250+x, 100, 0, 0.5, isLand6 - 1, 380, 500, 177,gameObjectGenerator(2250+x+100, 150,380)));
            islands.add(new Island(2750+x, 200, 0, 0.3, isLand7 - 1, 320, 350, 70,gameObjectGenerator(2750+x+100, 180,320)));
            islands.add(new Island(3200+x, 100, 0, 0, isLand8 - 1, 150, 400, 210,gameObjectGenerator(3200+x+20, 100,150)));
            islands.add(new Island(3420+x, 100, 0, 0.5, isLand9 - 1, 380, 480, 177,gameObjectGenerator(3420+x+120, 100,380)));
            islands.add(new Island(3900+x, 20, 0, 0, isLand10 - 1, 440, 520, 265,gameObjectGenerator(3900+x+150, 100,440)));
            x+=4400;
        }
        x=0;
        ArrayList<Orc> orcs = new ArrayList<>();
        Hero hero = new Hero(100,250,0,2,70,70, islands.get(0));
        Orc greenOrc = new Normal_G_Orc(450,250,0,2.5,70,70);
        orcs.add(greenOrc);
        ArrayList<Coin> coins=new ArrayList<>();
        x=0;
        for(int i=0;i<3;i++){
            coins.add(new Coin(340+x,160,0,0,30,30));
            x+=40;
        }
        root.getChildren().add(fxmlLoader.load());
        root.getChildren().add(sky.load());
        GamePlayController gamePlayController = fxmlLoader.getController();
        gamePlayController.initData(root, hero, islands, orcs, mediaPlayer,coins);
        scene = new Scene(root,1000,600);
        stage.setScene(scene);
        stage.show();
    }
    public Game_objects gameObjectGenerator(int x, int y, int width){
        Random rand = new Random();
        int randInteger = 1 + rand.nextInt(3);
        if(randInteger == 1){
            Game_objects tnt = new TNT(x,y,0,0,70,70);
            return tnt;
        }else if(randInteger == 2){
            Chests chest = new Weapon_chest(x,y,0,100,100,70);
            return chest;
        }
        return null;
    }
    public ImageView getMusic() {
        return music;
    }
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public Group getRoot() {
        return root;
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