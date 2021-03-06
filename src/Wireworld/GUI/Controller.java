package Wireworld.GUI;

import Wireworld.Core.*;
import Wireworld.Core.GameOfLife.GameOfLifeCellChecker;
import Wireworld.Core.WireWorld.WireworldCellChecker;
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
import java.util.Timer;
import java.util.TimerTask;


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
    private ComboBox CellSelector;
    @FXML
    private ComboBox SimulationSelector;
    @FXML
    private Text topText;
    @FXML
    public Canvas canvas;
    @FXML
    private VBox centerVBox;
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

    private Timer timer;
    private TimerTask timerTask;

    public double cellSize;
    private boolean timerPaused;
    private boolean wasTimerPaused;
    private int delay;

    @FXML
    void simulationSpeedSliderDragged() {
        simulationSpeedLabel.setText("Szybkość symulacji: " + Math.round(simulationSpeedSlider.getValue()));
        delay = 970 - (int)Math.round(simulationSpeedSlider.getValue()) * 9;
        runTimer();
    }

    @FXML
    void exitMenuOnAction() {
        stopSimulation();
        System.exit(0);
    }

    @FXML
    void simulationSelectorOnAction() {
        stopSimulation();
        if(SimulationSelector.getSelectionModel().getSelectedIndex() == 0)
            switchToWireworld();
        else if(SimulationSelector.getSelectionModel().getSelectedIndex() == 1)
            switchToGameOfLife();
    }

    @FXML
    void openFileMenuOnAction() {
        stopSimulation();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wczytaj plik z planszą");
        fileChooser.setInitialDirectory(new File("data"));

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki tekstowe (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(Main.stage);
        if(selectedFile != null) {
            simulation.loadGridFromFile(selectedFile.getPath());
            grid = simulation.getGrid();

            RowsSlider.setValue(simulation.getGrid().getRows());
            ColumnsSlider.setValue(simulation.getGrid().getColumns());

            refresh();
        }
    }

    @FXML
    void clearMenuOnAction() {
        stopSimulation();
        simulation.clear();
        refresh();
    }

    @FXML
    void newFileMenuOnAction () {
        stopSimulation();
        simulation.clear();

        RowsSlider.setValue(15);
        ColumnsSlider.setValue(25);

        ZoomSlider.setValue((ZoomSlider.getMax() + ZoomSlider.getMin())/2);
        zoomSliderDragged();

        simulation.getGrid().resize((int)RowsSlider.getValue(), (int)ColumnsSlider.getValue());
        refresh();
    }

    @FXML
    void saveFileMenuOnAction () {
        stopSimulation();
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
        if(startButton.getText().equals("START"))
            startSimulation();
        else
            stopSimulation();
    }

    @FXML
    void zoomSliderDragged() {
        cellSize = ZoomSlider.getValue()* 10;
        refresh();
    }

    @FXML
    void RowsSliderDragged() {
        stopSimulation();
        grid.resize((int)Math.round(RowsSlider.getValue()), grid.getColumns());
        refresh();

    }

    @FXML
    void ColumnsSliderDragged() {
        stopSimulation();
        grid.resize(grid.getRows(), (int)Math.round(ColumnsSlider.getValue()));
        refresh();
    }

    // Rysowanie komórek na siatce przy pomocy myszy i zatrzymywanie symulacji po najechaniu myszą na Canvas
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
        canvas.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        wasTimerPaused = timerPaused;
                        if(!wasTimerPaused)
                            stopSimulation();
                    }
                });
        canvas.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(!wasTimerPaused)
                            startSimulation();
                    }
                });

    }
    void handleCanvasDrawing(MouseEvent event) {
        int row = (int)((event.getY() - event.getY()%cellSize)/cellSize);
        int column = (int)((event.getX() - event.getX()%cellSize)/cellSize);

        if (row < grid.getRows() && column < grid.getColumns() && row >= 0 && column >= 0) {
            grid.getCell(row, column).setState(CellSelector.getSelectionModel().getSelectedIndex());
            CanvasUtils.printCell(row, column, cellSize, gc, grid.getCell(row, column));
        }
    }

    public void refresh() {
        topText.setText("Generacja: " + simulation.getCurrentGeneration() + " | Populacja: " + simulation.getPopulation());
        CanvasUtils.printGrid(grid, cellSize, gc);
    }

    void runTimer() {
        if(timerTask != null)
            timerTask.cancel();

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(!timerPaused) {
                    simulation.nextGeneration();
                    refresh();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 100, delay);
    }


    @FXML
    void initialize() {
        Main.controller = this;

        SimulationSelector.getItems().setAll("Wireworld", "Gra w życie");

        cellSize = ZoomSlider.getValue()* 10;

        centerVBox.setMinSize(1170, 800);
        ZoomSlider.setValue((ZoomSlider.getMax() + ZoomSlider.getMin())/2);


        timerPaused = true;

        gc = canvas.getGraphicsContext2D();
        initializeCanvasEventHandler();
        switchToWireworld();
        zoomSliderDragged();
        simulationSpeedSliderDragged();

        RowsSlider.setValue(simulation.getGrid().getRows());
        ColumnsSlider.setValue(simulation.getGrid().getColumns());
    }

    @FXML
    void startSimulation() {
        startButton.setText("STOP");
        timerPaused = false;
        runTimer();
    }

    @FXML
    void stopSimulation() {
        startButton.setText("START");
        timerPaused = true;
    }

    @FXML
    void switchToWireworld() {
        stopSimulation();
        ICellChecker cellChecker = new WireworldCellChecker();
        grid = FileIO.createEmpty(15, 25, cellChecker);
        SimulationSelector.getSelectionModel().select(0);

        CellSelector.getItems().setAll("Pusta komórka", "Głowa elektronu", "Ogon elektronu", "Przewodnik");
        CellSelector.getSelectionModel().select(CellSelector.getItems().size() - 1);

        simulation = new Simulation(grid, cellChecker);
        refresh();
    }

    @FXML
    void switchToGameOfLife() {
        stopSimulation();
        ICellChecker cellChecker = new GameOfLifeCellChecker();
        grid = FileIO.createEmpty(15, 25, cellChecker);

        CellSelector.getItems().setAll("Martwa komórka", "Żywa komórka");
        CellSelector.getSelectionModel().select(CellSelector.getItems().size() - 1);
        SimulationSelector.getSelectionModel().select(1);

        simulation = new Simulation(grid, cellChecker);
        refresh();
    }
}
