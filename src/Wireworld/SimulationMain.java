package Wireworld;

import java.io.IOException;

public class SimulationMain {
    public static void main(String path) {
        System.out.println("Witaj w symulacji Wireworld, otwieram podany plik z danymi:" + " " +  path);
        try {
            Grid grid = fileReader.readFile(path);
            Options opt = new Options(4,2000);
            ( new Simulation() ).simulator(opt,grid);

        }catch (IOException e){
            System.err.println("Nie istnieje podany plik");
        }catch(BlankFileException e) {
            System.err.println("Podano pusty plik");
        }catch (IllegalArgumentException e){
            System.err.println("Podano nieprawidłowe dane");
        }

    }
}

