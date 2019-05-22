package Wireworld.Core;

import java.io.*;

public class FileIO {
    public static Grid readFile(String path, ICellChecker cellChecker) throws IOException, IllegalArgumentException, BlankFileException {

        //Rozpoczynam wczytanie pliku od odczytania pierwszej lini poprzez użycie funkcji readFirstLine, zapisuje wymiary siatki
        BufferedReader bf = new BufferedReader(new FileReader(path));
        String lineContainer;

        AbstractCell tmp = cellChecker.createNewCell();
        int maxState = tmp.getMaxState() - 1;

        int[] sizeXY = readFirstLine(bf);
        int sizeY = sizeXY[0];
        int sizeX = sizeXY[1];
        //Rozpoczynam odczyt siatki
        AbstractCell[][] grid = new AbstractCell[sizeY][sizeX];
        int numOfLine = 0;

        while ((lineContainer = bf.readLine()) != null) {
            if (numOfLine >= sizeY) {
                throw new IllegalArgumentException();
            }
            String gridLine[] = lineContainer.split("\\s+"); //Dzielę linie na pojedyńcze String zawierające wartości komórek
            if (gridLine.length != sizeX)
                throw new IllegalArgumentException();
            for (int i = 0; i < sizeX; i++) { //i staje się numerem kolumny
                if (Character.isDigit((gridLine[i]).charAt(0))) {
                    if(Integer.parseInt(gridLine[i].charAt(0) + "") > maxState) {
                        throw new IllegalArgumentException("Błędny stan komórki!");
                    }
                    AbstractCell cell = cellChecker.createNewCell();
                    cell.setState(Character.getNumericValue(gridLine[i].charAt(0)));
                    grid[numOfLine][i] = cell;
                    //grid[numOfLine][i] = new WireworldCell(Character.getNumericValue(gridLine[i].charAt(0)));
                }
                else
                    throw new IllegalArgumentException("Błędna zawartość pliku!");
            }
            numOfLine++;
        }
        bf.close();
        return new Grid(grid);
    }

    public static int[] readFirstLine(BufferedReader bf) throws IOException, BlankFileException, IllegalArgumentException {
        //Funkcja za pomocą której wczytana zostaje pierwsza linia kodu
        String lineContainer;

        if ((lineContainer = bf.readLine()) != null) {
            String[] givenXY = lineContainer.split("\\s+"); //Rozbijam pierwszą linie na String zawierające wymiary
            if (givenXY.length != 2)
                throw new IllegalArgumentException();//Wyrzucam błąd gdy wymiarów nie jest dokładnie dwa
            int[] sizeXY = new int[2];
            sizeXY[0] = Integer.parseInt(givenXY[0]); //Jeśli konwersja na int nie zostanie spełniona wyrzucony zostanie błąd IllegalArgumentException
            sizeXY[1] = Integer.parseInt(givenXY[1]);
            return sizeXY;
        } else
            throw new BlankFileException(); //Jeśli nie istnieje pierwsza linia wyrzuci błąd
    }

    public static void writeFile(Grid grid, String path) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(grid.getRows() + " " + grid.getColumns() + '\n');

            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    bufferedWriter.write(grid.getGrid()[row][col].getState() + " ");
                }
                bufferedWriter.append('\n');
            }
            bufferedWriter.close();
        }catch(IOException e) {
            System.out.println("Nie udało się utworzyć pliku");
        }
    }

    public static Grid createEmpty(int rows, int columns, ICellChecker cellChecker) {
        AbstractCell[][] grid = new AbstractCell[rows][columns];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                grid[i][j] = cellChecker.createNewCell();
            }
        }
        return new Grid(grid);
    }
}
