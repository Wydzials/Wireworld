package Wireworld;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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
    public Canvas canvas;
    @FXML
    private VBox centerVBox;

    public final int rectSize = 15;
    public GraphicsContext gc;

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
        File selectedFile = fileChooser.showOpenDialog(Main.stage);
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
        SimulationMain.main("data/dane.txt");
        Main.controller = this;
        selector.getItems().removeAll(selector.getItems());
        selector.getItems().addAll("Pusta komórka", "Przewodnik", "Głowa elektronu", "Ogon elektronu");

        centerVBox.setMinSize(1200, 750);
        canvas.setHeight(900);
        canvas.setWidth(1000);
        gc = canvas.getGraphicsContext2D();
        showGrid(Main.grid);
    }

    public void showGrid(Grid grid) {
        int rectSize = 15;
        Rectangle rect;
        int columns = grid.getSizeX();
        int rows = grid.getSizeY();
        centerVBox.setMinSize(columns*rectSize + 1, rows*rectSize + 20);

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++) {
                Color color = (i+j)%2 == 0 ? Color.BLACK : Color.RED;
                printCell(j, i, color);
            }
    }
    private void printCell(int x, int y, Color color) {
        gc.setFill(color);
        gc.setStroke(Color.DARKGREY);
        gc.setLineWidth(1);
        gc.fillRect(x*rectSize, y*rectSize, rectSize, rectSize);
        gc.strokeRect(x*rectSize, y*rectSize, rectSize, rectSize);
    }


}
