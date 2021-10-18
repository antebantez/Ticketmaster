import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketMachine {
    static public String myDepartureString;
    static public String myDestinationString;
    static public int childTicketAmount;
    static public int adultTicketAmount;
    static public int pensionerTicketAmount;
    MenuClass menuClass = new MenuClass();
    String line = null;

    Scanner myScanner = new Scanner(System.in);
    ArrayList<String> stationList = new ArrayList<>();

    public void printDepartureOptions() {
        childTicketAmount = 0;
        adultTicketAmount = 0;
        pensionerTicketAmount = 0;

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
        System.out.println("\nWhere do you want to travel from?");
        System.out.println("Write -Menu- to get to the main menu");
        try {
            setMyDepartureString(myScanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String stations : stationList) {
            if (myDepartureString.equalsIgnoreCase(stations)){
                System.out.println("\nYou are travelling from " + stations);
                choseDestination();
            }

        }
    }

    public void choseDestination() {
        System.out.println(myDepartureString + "test");
        for (String stations : stationList) {
            System.out.println(stations);
        }
        System.out.println("\n\nWhich place do you want to travel to?\n");
        try{
            setMyDestinationString(myScanner.nextLine());

        }catch (Exception e){
            System.out.println("Wrong input, Try again!");
            choseDestination();
        }
        if (myDepartureString.equalsIgnoreCase(myDestinationString)){
            System.out.println("You cannot travel with train to the same location!\n" + "Try again!\n");
            choseDestination();
        }
        for (String stations : stationList) {

            if (myDestinationString.equalsIgnoreCase(stations)) {
                System.out.println("\n\nYou chose to travel to " + stations);
                System.out.println(myDepartureString + "t");
                ticketAmount();
            }
        }

    }

    public void ticketAmount() {
        Payment payment = new Payment();
        boolean isRunning = true;
        while (isRunning) {
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
            System.out.println(myDepartureString + "☺");
            switch (myScanner.nextInt()) {
                case 1 -> childTicketAmount++;
                case 2 -> adultTicketAmount++;
                case 3 -> pensionerTicketAmount++;
                case 0 -> {
                    isRunning = false;
                    payment.payForTickets();
                }
                default -> System.err.println("Please enter a valid option!");
            }
        }
    }


    public void travelRoutes() {
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

    public String getMyDepartureString() {
        return this.myDepartureString;
    }

    public String getMyDestinationString() {
        return this.myDestinationString;
    }

    public int getChildTicketAmount() {
        return this.childTicketAmount;
    }

    public int getAdultTicketAmount() {
        return this.adultTicketAmount;
    }

    public int getPensionerTicketAmount() {
        return this.pensionerTicketAmount;
    }

   public void setMyDepartureString(String myDepartureString) {
        this.myDepartureString = myDepartureString;
    }

    public void setMyDestinationString(String myDestinationString) {
        this.myDestinationString = myDestinationString;
    }

   /* public void setChildTicketAmount(int childTicketAmount) {
        this.childTicketAmount = childTicketAmount;
    }

    public void setAdultTicketAmount(int adultTicketAmount) {
        this.adultTicketAmount = adultTicketAmount;
    }

    public void setPensionerTicketAmount(int pensionerTicketAmount) {
        this.pensionerTicketAmount = pensionerTicketAmount;
    }*/
}
