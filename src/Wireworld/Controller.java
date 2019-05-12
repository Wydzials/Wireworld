package Wireworld;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;


public class Controller {
    @FXML
    private Label simulationSpeedLabel;
    @FXML
    private Slider simulationSpeedSlider;
    @FXML
    private Button startButton;
    @FXML
    private Button NextGenerationButton;
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
    @FXML
    private Label ZoomLabel;
    @FXML
    private Slider ZoomSlider;
    @FXML
    public ScrollPane ScrollPane;
    @FXML
    private Slider RowsSlider;
    @FXML
    private Slider ColumnsSlider;

    public GraphicsContext gc;

    public Simulation simulation;
    public Grid grid;

    public double cellSize;

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
        if(selectedFile != null) {
            console.setText(console.getText() + "\n Otwieram plik: " + selectedFile.getAbsolutePath());
        }
    }

    @FXML
    void nextGenerationButtonOnAction() {
        simulation.nextGeneration();
        grid.printGrid();
        CanvasUtils.printGrid(grid, cellSize, gc);
    }

    @FXML
    void startButtonOnAction() {
        if(startButton.getText().equals("START")) {
            startButton.setText("STOP");
        }
        else
            startButton.setText("START");
    }

    @FXML
    void zoomSliderDragged() {
        cellSize = ZoomSlider.getValue()* 10;
        CanvasUtils.printGrid(grid, cellSize, gc);
    }

    @FXML
    void scrollPaneClicked() {
    }

    @FXML
    void RowsSliderDragged() {
        grid.resize((int)Math.round(RowsSlider.getValue()), grid.getColumns());
        CanvasUtils.printGrid(grid, cellSize, gc);

    }

    @FXML
    void ColumnsSliderDragged() {
        grid.resize(grid.getRows(), (int)Math.round(ColumnsSlider.getValue()));
        CanvasUtils.printGrid(grid, cellSize, gc);
    }


    // Rysowanie komórek na siatce przy pomocy myszy
    void initializeCanvasEventHandler() {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        handleCanvasDrawing(event);
                    }
                });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        handleCanvasDrawing(event);
                    }
                });
    }
    void handleCanvasDrawing(MouseEvent event) {
        int row = (int)((event.getY() - event.getY()%cellSize)/cellSize);
        int column = (int)((event.getX() - event.getX()%cellSize)/cellSize);
        System.out.println(event.getX() + " " + event.getY() + " (" + row + ", " + column + ")");

        if (row < grid.getRows() && column < grid.getColumns()) {
            Cell.State newState = Cell.State.values()[selector.getSelectionModel().getSelectedIndex()];
            grid.getCell(row, column).setState(newState);
            CanvasUtils.printGrid(grid, cellSize, gc);
        }
    }



    @FXML
    void initialize() {
        Main.controller = this;
        selector.getItems().addAll("Pusta komórka", "Głowa elektronu", "Ogon elektronu", "Przewodnik");
        selector.getSelectionModel().select(0);
        cellSize = ZoomSlider.getValue()* 10;

        centerVBox.setMinSize(1200, 750);

        gc = canvas.getGraphicsContext2D();
        initializeCanvasEventHandler();

        try {
            grid = fileReader.readFile("data/dane3.txt");
        }catch (IOException e){
            System.err.println("Nie istnieje podany plik");
        }catch(BlankFileException e) {
            System.err.println("Podano pusty plik");
        }
        simulation = new Simulation(grid);
        CanvasUtils.printGrid(grid, cellSize, gc);
    }
}
