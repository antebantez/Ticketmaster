import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketMachine {
    String line = null;
    String myDepartureString;
    String myDestinationString;

    Scanner myScanner = new Scanner(System.in);
    ArrayList<String> stationList = new ArrayList<>();

    public void printDepartureOptions() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Stations.txt"));
            System.out.println("Departure locations:");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                stationList.add(line);
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        choseDeparture();
    }

    public void choseDeparture() {
        Menu menu = new Menu();
        System.out.println("\nWhere do you want to travel from?");
        System.out.println("Write -Menu- to get to the main menu");
        try{
            myDepartureString = myScanner.nextLine();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (String stations : stationList) {
            if (myDepartureString.equalsIgnoreCase(stations)) {
                try {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(150);
                        System.out.print("█   ");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\nYou are travelling from " + stations);
                choseDestination();
            }else if(myDepartureString.equalsIgnoreCase("Menu")){
                menu.mainMenu();
            }
        }
    }

    public void choseDestination() {
        for (String stations : stationList) {
            System.out.println(stations);
        }
        System.out.println("\n\nWhich place do you want to travel to?\n");
        myDestinationString = myScanner.nextLine();
        for (String stations : stationList) {
            if (myDestinationString.equalsIgnoreCase(stations)) {
                try {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(150);
                        System.out.print("█   ");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\n\nYou chose to travel to " + stations);
                //NY METOD HÄR, PRISER OCH ANTAL
            }
        }
    }

    public void travelRoutes() {
        Menu menu = new Menu();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("avgangar.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);

            }
            bufferedReader.close();
            menu.mainMenu();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getMyDepartureString() {
        return myDepartureString;
    }

    public String getMyDestinationString() {
        return myDestinationString;
    }
}
