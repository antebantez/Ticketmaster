import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketMachine {
    String line = null;
    String myDepartureString;
    String myDestinationString;
    int childTicketAmount;
    int adultTicketAmount;
    int pensionerTicketAmount;
    MenuClass menuClass = new MenuClass();



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
            System.out.println(myDepartureString);
        } catch (Exception e) {
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
            } //else if (myDepartureString.equalsIgnoreCase("Menu")) {
//                menuClass.mainMenu();
//            }

        }
    }

    public void choseDestination() {
        System.out.println(myDepartureString + "test");
        for (String stations : stationList) {
            System.out.println(stations);
        }
        System.out.println("\n\nWhich place do you want to travel to?\n");
        setMyDestinationString(myScanner.nextLine());
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
                System.out.println(myDepartureString + "t");
                ticketAmount();
            }
//            else if (myDestinationString.equalsIgnoreCase("Menu")){
//                menuClass.mainMenu();
//            }

        }

    }

    public void ticketAmount() {
        Payment payment = new Payment();
        while (true) {
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
                case 1:
                    childTicketAmount++;
                    setChildTicketAmount(childTicketAmount);
                    break;
                case 2:
                    adultTicketAmount++;
                    setAdultTicketAmount(adultTicketAmount);
                    break;
                case 3:
                    pensionerTicketAmount++;
                    setPensionerTicketAmount(pensionerTicketAmount);
                    break;
                case 0:
                    payment.payForTickets();
                    break;
                default:
                    System.err.println("Please enter a valid option!");
                    break;
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
        System.out.println(myDepartureString);
        return myDepartureString;
    }

    public String getMyDestinationString() {
        return myDestinationString;
    }

    public int getChildTicketAmount() {
        return childTicketAmount;
    }

    public int getAdultTicketAmount() {
        return adultTicketAmount;
    }

    public int getPensionerTicketAmount() {
        return pensionerTicketAmount;
    }

    public void setMyDepartureString(String myDepartureString) {
        this.myDepartureString = myDepartureString;
    }

    public void setMyDestinationString(String myDestinationString) {
        this.myDestinationString = myDestinationString;
    }

    public void setChildTicketAmount(int childTicketAmount) {
        this.childTicketAmount = childTicketAmount;
    }

    public void setAdultTicketAmount(int adultTicketAmount) {
        this.adultTicketAmount = adultTicketAmount;
    }

    public void setPensionerTicketAmount(int pensionerTicketAmount) {
        this.pensionerTicketAmount = pensionerTicketAmount;
    }
}
