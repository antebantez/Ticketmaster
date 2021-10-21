import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketMachine {
    //this is variables that is going to get where I am travelling to/from and the amount of tickets
    String myDepartureString;
    String myDestinationString;
    int childTicketAmount = 0;
    int adultTicketAmount = 0;
    int pensionerTicketAmount = 0;
    String line = null;
    Scanner myScanner = new Scanner(System.in);
    //Declares an arraylist that we can store all the stations and later on compare
    ArrayList<String> stationList = new ArrayList<>();

    //Method for printing all the available stations in the "Stations.txt" and
    // adding them to the arraylist using a bufferedReader
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

    //A method to get where the user want to travel from
    public void choseDeparture() {
        System.out.println("\nWhere do you want to travel from?");
        System.out.println("Write -Menu- to get to the main menu");
        try {
            myDepartureString = myScanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //if the input equals "Menu" then the user will be sent back to the main menu
        if (myDepartureString.equalsIgnoreCase("menu")) {
            MenuClass menu = new MenuClass();
            menu.mainMenu();
        }
        //here we will compare if the input equals any of the stations that we have
        //added in the arraylist and then print where the user is travelling from
        for (String stations : stationList) {
            if (myDepartureString.equalsIgnoreCase(stations)) {
                System.out.println("\nYou are travelling from " + stations);
                choseDestination();
            }

        }
    }

    //Method to get where the user wants to travel
    public void choseDestination() {
        for (String stations : stationList) {
            System.out.println(stations);
        }
        System.out.println("\n\nWhich place do you want to travel to?\n");
        System.out.println("Write -Menu- to get to the main menu");
        try {
            myDestinationString = myScanner.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("Wrong input, Try again!");
            choseDestination();
        }
        //if the departure-station is the same as the destination-station then we will tell the user
        // that it's not possible, and they will have to choose again
        if (myDepartureString.equalsIgnoreCase(myDestinationString)) {
            System.out.println("You cannot travel with train to the same location!" + "\nTry again!");
            choseDestination();
        }
        //if the input is equal to "menu" then the user will get sent to the main menu
        if (myDestinationString.equalsIgnoreCase("menu")) {
            MenuClass menu = new MenuClass();
            menu.mainMenu();
        }
        //here we will compare if the input equals any of the stations that we have
        //added in the arraylist and then print where the user wants to travel
        for (String stations : stationList) {
            if (myDestinationString.equalsIgnoreCase(stations)) {
                System.out.println("\n\nYou chose to travel to " + stations);
                ticketAmount();
            }
        }
    }

    //This method shows how many tickets the user has in real-time with an opportunity to add different
    //kinds of tickets which is displayed on the screen
    public void ticketAmount() {
        Payment payment = new Payment();
        boolean isRunning = true;
        do {
            System.out.println("Child tickets : " + childTicketAmount);
            System.out.println("Adult tickets : " + adultTicketAmount);
            System.out.println("pensioner tickets : " + pensionerTicketAmount);
            System.out.println("-----------------------------------------------");
            System.out.println("""
                    To add tickets please press :\s
                    '1' for child ticket
                    '2' for adult ticket
                    '3' for pensioner ticket
                    Press '0' to continue to payment""");
            try {
                //this switch will add the ticket
                switch (myScanner.nextInt()) {
                    case 1 -> childTicketAmount++;
                    case 2 -> adultTicketAmount++;
                    case 3 -> pensionerTicketAmount++;
                    //when the user is finished '0' we will send all the values we have gathered to the Payment-class
                    case 0 -> {
                        isRunning = false;
                        payment.payForTickets(myDepartureString, myDestinationString,
                                childTicketAmount, adultTicketAmount, pensionerTicketAmount);
                    }
                    default -> System.err.println("Please enter a valid option!");
                }
                //if the user tries to enter anything else than a number this catch
                // will send the user to the beginning of the method
            } catch (InputMismatchException e) {
                String clearScanner = myScanner.next();
                ticketAmount();
            }
        } while (isRunning);
    }

    //this method displays all the possible routes that the user can take
    public void travelRoutes() {
        MenuClass menuClass = new MenuClass();
        //here I use a bufferedReader to print all the lines in the "avgangar.txt"
        //and then send the user back to the main menu
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("avgangar.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);

            }
            bufferedReader.close();
            menuClass.mainMenu();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
