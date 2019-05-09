package Wireworld;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static Stage stage;
    public static Controller controller;
    public static Grid grid;

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        stage.setTitle("Wireworld");
        stage.setScene(new Scene(root));
        stage.setResizable(true);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.show();
        this.stage = stage;
    }

    public static void update() {
        System.out.println("XD");
        controller.showGrid(grid);
    }


    public static void main(String[] args) {
        SimulationMain.main("data/dane.txt");
        //launch(args);
    }
}


