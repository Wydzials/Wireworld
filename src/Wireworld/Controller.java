package Wireworld;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
    @FXML
    private MenuItem ClearMenu;


    public GraphicsContext gc;

    public Simulation simulation;
    public Grid grid;

    public double cellSize;

    @FXML
    void simulationSpeedSliderDragged() {
        simulationSpeedLabel.setText("Szybkość symulacji: " + Math.round(simulationSpeedSlider.getValue()));
        simulation.setDelay(10000/(int)Math.round(simulationSpeedSlider.getValue()));
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
        fileChooser.setTitle("Wczytaj plik z planszą");
        fileChooser.setInitialDirectory(new File("data"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki tekstowe (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(Main.stage);
        if(selectedFile != null) {
            console.setText(console.getText() + "\n Otwieram plik: " + selectedFile.getAbsolutePath());
            simulation.loadGridFromFile(selectedFile.getPath());
            grid = simulation.getGrid();
            refresh();
        }
    }

    @FXML
    void clearMenuOnAction() {
        simulation.clear();
        refresh();
    }

    @FXML
    void newFileMenuOnAction () {
        simulation.clear();
        simulation.getGrid().resize(15, 25);
        ZoomSlider.setValue((ZoomSlider.getMax() + ZoomSlider.getMin())/2);
        zoomSliderDragged();
        refresh();
    }

    @FXML
    void saveFileMenuOnAction () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz plik z planszą");
        fileChooser.setInitialDirectory(new File("data"));
        fileChooser.setInitialFileName("wireworld");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki tekstowe (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(Main.stage);
        if (file != null) {
            FileIO.writeFile(simulation.getGrid(), file.getPath());
        }
    }

    @FXML
    void nextGenerationButtonOnAction() {
        simulation.nextGeneration();
        refresh();
    }


    @FXML
    void startButtonOnAction() {
        if(startButton.getText().equals("START")) {
            startButton.setText("STOP");
            simulation.setPaused(false);
        }
        else {
            startButton.setText("START");
            simulation.setPaused(true);
        }
    }

    @FXML
    void zoomSliderDragged() {
        cellSize = ZoomSlider.getValue()* 10;
        refresh();
    }

    @FXML
    void scrollPaneClicked() {
    }

    @FXML
    void RowsSliderDragged() {
        grid.resize((int)Math.round(RowsSlider.getValue()), grid.getColumns());
        refresh();

    }

    @FXML
    void ColumnsSliderDragged() {
        grid.resize(grid.getRows(), (int)Math.round(ColumnsSlider.getValue()));
        refresh();
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
        //System.out.println(event.getX() + " " + event.getY() + " (" + row + ", " + column + ")");

        if (row < grid.getRows() && column < grid.getColumns() && row >= 0 && column >= 0) {
            grid.getCell(row, column).setState(selector.getSelectionModel().getSelectedIndex());
            refresh();
        }
    }

    void refresh() {
        topText.setText("Generacja: " + simulation.getCurrentGeneration() + " | Populacja: " + simulation.getPopulation());
        CanvasUtils.printGrid(grid, cellSize, gc);
    }


    @FXML
    void initialize() {
        Main.controller = this;
        selector.getItems().addAll("Pusta komórka", "Głowa elektronu", "Ogon elektronu", "Przewodnik");
        selector.getSelectionModel().select(selector.getItems().size() - 1);
        cellSize = ZoomSlider.getValue()* 10;

        centerVBox.setMinSize(1200, 750);
        ZoomSlider.setValue((ZoomSlider.getMax() + ZoomSlider.getMin())/2);

        gc = canvas.getGraphicsContext2D();
        initializeCanvasEventHandler();


        ICellChecker cellChecker = new WireworldCellChecker();
        try {
            grid = FileIO.readFile("data/dane3.txt", cellChecker);
        }catch (IOException e){
            System.err.println("Nie istnieje podany plik");
        }catch(BlankFileException e) {
            System.err.println("Podano pusty plik");
        }

        simulation = new Simulation(grid, cellChecker);
        refresh();
    }
}
