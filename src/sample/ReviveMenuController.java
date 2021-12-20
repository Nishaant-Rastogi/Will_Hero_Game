package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ReviveMenuController {
    @FXML
    private Text score;
    @FXML
    private Text coins;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void initData(GamePlayController gamePlayController, AnchorPane reviveMenu, Hero hero, Text score, Text coins){
        this.score.setText(score.getText());
        this.coins.setText(coins.getText());
    }
    public void mainMenu(javafx.scene.input.MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
