import java.io.IOException;

public class MMain {
    public static void main(String[] args) {
        System.out.println("Witaj w symulacji Wireworld, otwieram podany plik z danymi:" + " " +  args[0]);
        try {
            gridContainer grid = fileReader.readFile(args[0]);
            options opt = new options(4,2000);
            ( new simulation() ).simulator(opt,grid);

        }catch (IOException e){
            System.err.println("Nie istnieje podany plik");
        }catch(BlankFileException e) {
            System.err.println("Podano pusty plik");
        }catch (IllegalArgumentException e){
            System.err.println("Podano nieprawidłowe dane");
        }

    }

}
