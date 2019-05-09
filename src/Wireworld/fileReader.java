package Wireworld;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileReader {
    public static Grid readFile(String path) throws IOException, IllegalArgumentException, BlankFileException{

        //Rozpoczynam wczytanie pliku od odczytania pierwszej lini poprzez użycie funkcji readFirstLine, zapisuje wymiary siatki
        BufferedReader bf = new BufferedReader(new FileReader(path));
        String lineContainer;

        int [] sizeXY = readFirstLine(bf);
        int sizeY = sizeXY[0];
        int sizeX = sizeXY[1];
        //Rozpoczynam odczyt siatki
        Cell[][] grid = new Cell[sizeY][sizeX];
        int numOfLine = 0;

        while((lineContainer = bf.readLine()) != null){
            if(numOfLine >= sizeY){
                throw new IllegalArgumentException();
            }
            String gridLine[] = lineContainer.split("\\s+"); //Dzielę linie na pojedyńcze String zawierające wartości komórek
            if(gridLine.length != sizeX)
                throw new IllegalArgumentException();
            for(int i=0; i < sizeX; i++) { //i staje się numerem kolumny
                if(Character.isDigit( (gridLine[i]).charAt(0) ) )
                grid[numOfLine][i] = new Cell(Cell.State.values()[Character.getNumericValue(gridLine[i].charAt(0))]);
            }
            numOfLine++;
        }
        return new Grid(sizeX, sizeY, grid);
    }

    public static int[] readFirstLine(BufferedReader bf) throws IOException, BlankFileException, IllegalArgumentException{
        //Funkcja za pomocą której wczytana zostaje pierwsza linia kodu
        String lineContainer;

        if( (lineContainer = bf.readLine() ) != null) {
            String[] givenXY = lineContainer.split("\\s+"); //Rozbijam pierwszą linie na String zawierające wymiary
            if(givenXY.length != 2)
                throw new IllegalArgumentException();//Wyrzucam błąd gdy wymiarów nie jest dokładnie dwa
            int[] sizeXY = new int[2];
            sizeXY[0] = Integer.parseInt(givenXY[0]); //Jeśli konwersja na int nie zostanie spełniona wyrzucony zostanie błąd IllegalArgumentException
            sizeXY[1] = Integer.parseInt(givenXY[1]);
            return sizeXY;
        }else
            throw new BlankFileException(); //Jeśli nie istnieje pierwsza linia wyrzuci błąd
    }
}
