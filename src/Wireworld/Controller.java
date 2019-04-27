package Wireworld;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

import static java.lang.System.exit;


public class Controller {
    @FXML
    private Label simulationSpeedLabel;
    @FXML
    private Slider simulationSpeedSlider;
    @FXML
    private Button startButton;
    @FXML
    private MenuItem openFileMenu;
    @FXML
    private MenuItem exitMenu;
    @FXML
    private ComboBox selector;
    @FXML
    private Text console;
    @FXML
    private Text topText;
    @FXML
    private Pane pane;

    @FXML
    void simulationSpeedSliderDragged() {
        simulationSpeedLabel.setText("Szybkość symulacji: " + Math.round(simulationSpeedSlider.getValue()));
    }

    @FXML
    void exitMenuOnAction() {
        exit(0);
    }

    @FXML
    void selectorOnAction(){

    }

    @FXML
    void openFileMenuOnAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Otwórz plik z planszą");
        File selectedFile = fileChooser.showOpenDialog(Main.primaryStage);
        if(selectedFile != null)
            console.setText(console.getText() + '\n' + selectedFile.getAbsolutePath());
    }

    @FXML
    void startButtonOnAction() {
        if(startButton.getText().equals("START"))
            startButton.setText("STOP");
        else
            startButton.setText("START");
    }

        @FXML
    void initialize() {
        selector.getItems().removeAll(selector.getItems());
        selector.getItems().addAll("Pusta komórka", "Przewodnik", "Głowa elektronu", "Ogon elektronu");
        /*for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++) {
                Pane cellPane = new Pane();
                cellPane.setPrefSize(5, 5);
                GridPane.setFillHeight(cellPane, true);
                GridPane.setFillWidth(cellPane, true);
                gridPane.add(cellPane, i, j);
            }*/

            int columns = 40, rows = 60 , horizontal = 15, vertical = 15;
            Rectangle rect;
            for(int i = 0; i < columns; ++i)
            {
                for(int j = 0; j < rows; ++j)
                {
                    rect = new Rectangle(horizontal*j, vertical*i, horizontal, vertical);

                    rect.setStroke(Color.BLACK);
                    rect.setFill(Color.WHITE);

                    pane.getChildren().add(rect);
                }
            }
            pane.getChildren().remove(400, 410);
    }

}
