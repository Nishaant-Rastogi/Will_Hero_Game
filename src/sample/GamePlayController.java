package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GamePlayController implements Serializable {
    @FXML
    private transient ImageView setting;
    @FXML
    private transient Text score;
    @FXML
    private transient Text coinsCollected;
    @FXML
    private transient ImageView lance;
    private boolean lanceSelect;
    @FXML
    private transient ImageView sword;
    private boolean swordSelect;
    @FXML
    private transient Text lanceLevel;
    @FXML
    private transient Text swordLevel;
    private transient Group root;
    private transient MediaPlayer mediaPlayer;
    private ArrayList<Chests> chests;
    private transient Timeline time;
    private Hero hero;
    private ArrayList<Island> islands;
    private ArrayList<Obstacle> obs;
    private transient Button inputButton;
    private ArrayList<TNT> tnts;
    //private ArrayList<Coin> coins;
    private AtomicBoolean fall= new AtomicBoolean(false);
    public void initData(Group root, Hero hero,ArrayList<Island> islands,MediaPlayer mediaPlayer) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GamePlay.fxml"));
        this.mediaPlayer = mediaPlayer;
        this.root = root;
        this.hero = hero;
        this.islands = islands;
        //this.coins=c;
        final int[] cur = {0};
        this.tnts = new ArrayList<>();
        this.chests = new ArrayList<>();

        //fall used to check for fall condition
        //current island index;
        for(Island island : this.islands){
            try {
                island.makeImage(root);
                if(island.getObject() instanceof TNT){
                    tnts.add((TNT)island.getObject());
                }
                else{
                    chests.add((Chests) island.getObject());
                }
                island.getObject().makeImage(root);
            }catch (NullPointerException ignored){}
        }
        int heroIsland = 0;
        for(Island island : this.islands){
            if(heroIsland == 0) {
                heroIsland++;
                continue;
            }
            if(heroIsland != 18 && heroIsland != 19) {
                island.setOrcs(generateOrcs(island));
                for (Orc orc : island.getOrcs()) {
                    orc.makeImage(root);
                }
            }else if(heroIsland == 19){
                island.setOrcs(new ArrayList<Orc>());
                island.getOrcs().add(new Boss_orc(island.getImg().getX(),island.getImg().getY()+island.getBase(),0,2,150,150));
                island.getOrcs().get(0).makeImage(root);
            }
            heroIsland++;
        }
//        for (Coin coin : coins) {
//            coin.makeImage(root);
//        }
        hero.makeImage(root);
        root.getChildren().add(root.getChildren().remove(0));
        inputButton = new Button();
        inputButton.setStyle("-fx-background-color: transparent;");
        inputButton.setLayoutY(100);
        inputButton.setPrefWidth(800);inputButton.setPrefHeight(400);
        root.getChildren().add(inputButton);
        //see if hold can give a power up
        root.getChildren().get(root.getChildren().size()-1).setOnMousePressed(mouseEvent -> {
            fall.set(false);
            int count = mouseEvent.getClickCount();
            hero.getMove().play();
            score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            for (int i = 1; i < root.getChildren().size() - 3; i++) {
                if (root.getChildren().get(i) != hero.getImg())
                    ((ImageView) root.getChildren().get(i)).setX(((ImageView) root.getChildren().get(i)).getX() - 70);
            }

            for (int i = cur[0]; i < this.islands.size(); i++) {
                if ((this.islands.get(i).getImg().getX() + this.islands.get(i).getWidth()) >= hero.getImg().getX())   {
                    this.hero.setCurrIsland(this.islands.get(i));
                    cur[0] = i;
                    if (hero.getCurrIsland().getImg().getX() > hero.getImg().getX()+60) {
                        System.out.println("Fall");
                        fall.set(true);
                    }
                    if (hero.getCurrIsland().getImg().getX() + hero.getCurrIsland().getWidth() <= hero.getImg().getX()) {
                        System.out.println("Fall1");
                        fall.set(true);
                    } else {
                        break;
                    }
                }
            }
        });
        KeyFrame heroFrame = new KeyFrame(Duration.millis(11), e->{
            if(hero.getImg().getY()>=600){
//                System.exit(0);
//                change
                try {
                    reviveMenu();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(fall.get()){
                //make a function here
                hero.getImg().setY(hero.getImg().getY()+2);
            }
            else {
                if(hero.getImg().getY()>hero.getCurrIsland().getImg().getY()+hero.getCurrIsland().getBase()+0.6){
                    //System.out.println(hero.getImg().getY()+" "+(hero.getCurrIsland().getImg().getY()+hero.getCurrIsland().getBase()));
                    hero.getImg().setY(hero.getImg().getY()+2);
                }
                else{
                    hero.jump();
                }
            }
        });
        KeyFrame collideChestFrame = new KeyFrame(Duration.millis(11), e->{
            //refine the loop
            for (Chests chest : chests) {
                try {
                    if(chest.collide(hero)) {
                        if (chest instanceof Coin_chest) {
                            hero.getCoinCase().addAll(((Coin_chest) chest).getCoins());
                            this.getCoinsCollected().setText(Integer.toString(hero.getCoinCase().size()));
                        } else if (chest instanceof Weapon_chest) {
                            if(((Weapon_chest) chest).getWeapon() instanceof Lance){
                                if(swordSelect) {
                                    sword.setImage(new Image(new File("src/assets/SwordDisplay.png").toURI().toString()));
                                    swordSelect = false;
                                }
                                lance.setImage(new Image(new File("src/assets/selectLance.png").toURI().toString()));
                                lanceSelect = true;
                                ((Weapon_chest) chest).getWeapon().upgradeWeapon(Integer.parseInt(lanceLevel.getText()));
                                lanceLevel.setText(Integer.toString(((Weapon_chest) chest).getWeapon().getLevel()));

                            }else{
                                if(lanceSelect){
                                    lance.setImage(new Image(new File("src/assets/LanceDisplay.png").toURI().toString()));
                                    lanceSelect = false;
                                }
                                sword.setImage(new Image(new File("src/assets/selectSword.png").toURI().toString()));
                                swordSelect = true;
                                ((Weapon_chest) chest).getWeapon().upgradeWeapon(Integer.parseInt(swordLevel.getText()));
                                swordLevel.setText(Integer.toString(((Weapon_chest) chest).getWeapon().getLevel()));
                            }
                            hero.setWeapon(((Weapon_chest) chest).getWeapon(), root);
                        }
                    }
                } catch (NullPointerException ignore) {}
            }
        });
        KeyFrame orcCrushFrame = new KeyFrame(Duration.millis(11), e->{
            Orc orc;
            try{
                for(int i=0;i< hero.getCurrIsland().getOrcs().size();i++){
                    orc=hero.getCurrIsland().getOrcs().get(i);
                    if(orc.collide(hero)){
                        AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/heroDie.mp3")).toExternalForm());
                        buzzer.play();
                        reviveMenu();
                    }
                    else{
                        if(orc.isDead()){
                            coinsCollected.setText(Integer.toString(hero.getCoinCase().size()));
                            hero.getCurrIsland().getOrcs().remove(i);
                            //root
                            i--;
                        }else{

                        }
                    }
                }
            }catch(NullPointerException | IOException ignore){

            }
        });
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            for (Island island : this.islands) {
                try {
                    if (island.getObject() instanceof Coin_chest)
                        island.getObject().getImg().setY(island.getImg().getY() + island.getBase() - 20);
                    else island.getObject().getImg().setY(island.getImg().getY() + island.getBase());
                } catch (NullPointerException ignored) {}
                if(island.getOrcs().size() > 0) {
                    for (Orc orc : island.getOrcs()) {
                        orc.setCurrIsland(island);
                        orc.jump();
                    }
                }
                island.jump();
            }

        });
        KeyFrame weaponFrame = new KeyFrame(Duration.millis(10), e->{
            if(hero.getWeapon() != null){
                hero.getWeapon().getImg().setX(hero.getImg().getX());
                hero.getWeapon().getImg().setY(hero.getImg().getY()+50);
            }
        });
        KeyFrame BossFrame = new KeyFrame(Duration.millis(10), e->{
            if(islands.get(19).getOrcs().size() > 0) {
                Boss_orc boss_orc = (Boss_orc) islands.get(19).getOrcs().get(0);
                if (boss_orc.collide(hero)) {
                    try {
                        reviveMenu();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    if (boss_orc.isDead()) {
                        coinsCollected.setText(Integer.toString(hero.getCoinCase().size()));
                        try {
                            System.out.println("Game won");
                            endGameMenu();
                        } catch (IOException ignored) {

                        }
                    }
                }
            }
        });
        KeyFrame tnt = new KeyFrame(Duration.millis(10), e->{
            for (TNT curTNT : tnts) {
                if (curTNT.collide(hero)) {
                    if (!hero.getIsAlive()) {
                        try {
                            AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/heroDie.mp3")).toExternalForm());
                            buzzer.play();
                            reviveMenu();
                        } catch (IOException ex) {
                            System.out.println("Error in tnt exp in game play controller");
                        }
                    } else {
                        System.out.println("Did not enter");
                    }
                }
            }
            if(!hero.getIsAlive()){
                try {
                    AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/heroDie.mp3")).toExternalForm());
                    buzzer.play();
                    reviveMenu();
                } catch (IOException ex) {
                    System.out.println("Error in tnt exp in game play controller");
                }
            }
        });
        this.time = new Timeline(heroFrame,frame,collideChestFrame,weaponFrame,orcCrushFrame,tnt,BossFrame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();

    }
    public ArrayList<Orc> generateOrcs(Island island){
        ArrayList<Orc> orcs= new ArrayList<>();
        Random randomOrc = new Random();
        int maxNo= randomOrc.nextInt(3); double x,y;
        Game_objects onIsland= island.getObject();

        for(int i=0;i<maxNo;i++){
            x=island.getImg().getX()+50;
            //y here was set randomly
            y=island.getImg().getY()+island.getBase();

            if(orcs.size() > 0 && island.getObject() != null) {
                if (x + 70 > orcs.get(orcs.size() - 1).getPosition()[0] || orcs.get(orcs.size() - 1).getPosition()[0] + 70 > x) {
                    x += Math.abs(x  - orcs.get(orcs.size() - 1).getPosition()[0])+ 70;
                }
                if (x + 140 > onIsland.getImg().getX() || onIsland.getImg().getX() + 140 > x) {
                    x += Math.abs(x - onIsland.getImg().getX())+140;
                }
            }else if(island.getObject() != null) {
                if (x + 140 > onIsland.getImg().getX() || onIsland.getImg().getX() + 140 > x) {
                    x += Math.abs(x - onIsland.getImg().getX())+140;
                }
            }else{
                if(orcs.size() > 0) {
                    if (x + 70 > orcs.get(orcs.size() - 1).getPosition()[0] || orcs.get(orcs.size() - 1).getPosition()[0] + 70 > x) {
                        x += Math.abs(x  - orcs.get(orcs.size() - 1).getPosition()[0])+ 70;
                    }
                }
            }
            if(x + 70 < island.getImg().getX()+island.getWidth()) {
                int orcChoice = 1 + randomOrc.nextInt(2);
                int speedChoice = 2 + randomOrc.nextInt(3);
                if (orcChoice == 1) orcs.add(new Normal_G_Orc(x, y, 0, speedChoice, 70, 70));
                else orcs.add(new Shield_R_Orc(x, y, 0, speedChoice, 70, 70));
            }
        }
        return orcs;
        //there may be no orcs
    }


    public Button getInputButton() {
        return inputButton;
    }

    private void reviveMenu() throws IOException {
        inputButton.setDisable(true);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ReviveMenu.fxml"));

        AnchorPane reviveMenu = fxmlLoader.load();
        reviveMenu.setLayoutX(300);
        reviveMenu.setLayoutY(60);
        root.getChildren().add(reviveMenu);
        time.pause();
        ReviveMenuController reviveMenuController = fxmlLoader.getController();
        reviveMenuController.initData(this,reviveMenu,hero, score, coinsCollected);
    }
    private void endGameMenu() throws IOException {
        AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/win.mp3")).toExternalForm());
        buzzer.play();
        inputButton.setDisable(true);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EndGame.fxml"));

        AnchorPane endGameMenu = fxmlLoader.load();
        endGameMenu.setLayoutX(300);
        endGameMenu.setLayoutY(60);
        root.getChildren().add(endGameMenu);
        time.pause();
        EndGameController endGameController = fxmlLoader.getController();
        endGameController.initData(this,endGameMenu,hero, score, coinsCollected);
    }
    public void pause() throws IOException {
        inputButton.setDisable(true);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PauseMenu.fxml"));

        AnchorPane pauseMenu = fxmlLoader.load();
        pauseMenu.setLayoutX(380);
        pauseMenu.setLayoutY(6);
        root.getChildren().add(pauseMenu);
        time.pause();
        PauseMenuController pauseMenuController = fxmlLoader.getController();
        if(this.mediaPlayer.isMute()) {
            pauseMenuController.getMusic().setImage(new Image(new File("src/assets/MusicButtonClose.png").toURI().toString()));
        }
        pauseMenuController.initData(this, pauseMenu,inputButton);
    }
    public void selectLance(MouseEvent mouseEvent){
        if(Integer.parseInt(lanceLevel.getText()) > 0 && !lanceSelect){
            lance.setImage(new Image(new File("src/assets/selectLance.png").toURI().toString()));
            sword.setImage(new Image(new File("src/assets/SwordDisplay.png").toURI().toString()));
            hero.setWeapon(new Lance(1,1,1),root);
            lanceSelect = true;
            swordSelect = false;
        }
    }
    public void selectSword(MouseEvent mouseEvent){
        if(Integer.parseInt(swordLevel.getText()) > 0 && !swordSelect){
            sword.setImage(new Image(new File("src/assets/selectSword.png").toURI().toString()));
            lance.setImage(new Image(new File("src/assets/LanceDisplay.png").toURI().toString()));
            hero.setWeapon(new Sword(1,1,1),root);
            swordSelect = true;
            lanceSelect = false;
        }
    }

    public Hero getHero() {
        return hero;
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }

    public Text getCoinsCollected() {
        return coinsCollected;
    }
    public Group getRoot() {
        return root;
    }
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public Timeline getTime() {
        return time;
    }
    public void shadowEffectSetting(MouseEvent mouseEvent) {
        setting.setEffect(new DropShadow(20, Color.BLACK));
    }
    public AtomicBoolean getFall() {
        return fall;
    }
    public void removeShadowEffectSetting(MouseEvent mouseEvent) {
        setting.setEffect(null);
    }
}