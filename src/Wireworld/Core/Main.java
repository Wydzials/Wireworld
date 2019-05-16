package Wireworld.Core;

import Wireworld.GUI.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    public static Stage stage;
    public static Controller controller;

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/GUI.fxml"));
        stage.setTitle("Elektryka prąd nie tyka | Symulator Wireworld");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        Image icon = new Image(getClass().getResourceAsStream("../GUI/icon.png"));
        stage.getIcons().add(icon);
        stage.show();
        this.stage = stage;
    }


    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
}


