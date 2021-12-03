
package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void pauseMenu(javafx.scene.input.MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PauseMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        String path = "src/assets/island_1.png";
        ImageView island = new ImageView(new File(path).toURI().toString());
        island.setX(100);
        island.setY(200);
        root.getChildren().add(fxmlLoader.load());
        root.getChildren().add(island);
        scene = new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();

    }


}