import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Payment {
    //this is an object to be able to print the current time of the purchase
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    MenuClass menu = new MenuClass();
    Scanner myScanner = new Scanner(System.in);
    //variables to be able to count the price and tickets
    int money = 0;
    int sumToPay = 0;
    int totalTickets = 0;
    int kidPrice = 45;
    int pensionerPrice = 50;
    int adultPrice = 60;

    //this method is for making the transaction
    public void payForTickets(String myDeparture, String myDestination, int childTicketAmount, int adultTicketAmount, int pensTicketAmount) {
        totalTickets = childTicketAmount + adultTicketAmount + pensTicketAmount;
        //this is the total price that the user is paying
        sumToPay = (childTicketAmount * kidPrice) + (adultTicketAmount * adultPrice) +
                (pensTicketAmount * pensionerPrice);

        //if either the departure-station or destination-station is "Köpenhamn C"
        //the program will add an extra 5 kr to every bought ticket
        if (myDeparture.equalsIgnoreCase("Köpenhamn C") ||
                myDestination.equalsIgnoreCase("Köpenhamn C")) {
            sumToPay += (5 * totalTickets);
        }
        //We print the total price to the user and ask for money-input
        System.out.println("Amount to pay : " + (sumToPay - money));
        System.out.println("Please insert money into the machine");
        money += myScanner.nextInt();
        //If money is greater than or equal to the sum to pay
        //then we will print the ticket and eventual money in return
        if (money >= sumToPay) {
            System.out.println("Payment succeeded!");
            System.out.println("You get " + (money - sumToPay) + " in return!");
            System.out.println("Printing ticket...");
            //This is a loading bar (just because it's cool)
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(150);
                    System.out.print("█   ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //we call the makeTicket method and pass the values needed to get a correct output
            makeTicket(myDeparture, myDestination, childTicketAmount, adultTicketAmount, pensTicketAmount);

        } else {
            //if the money is less that the sum to pay
            //then the user will have to try again but only pay the remaining amount
            System.out.println("You cant afford the tickets, please insert another " +
                    (sumToPay - money) + " Kr");
            payForTickets(myDeparture, myDestination, childTicketAmount, adultTicketAmount, pensTicketAmount);
        }
    }

    //This method is making the ticket with all the parameters that we have passed to it
    public void makeTicket(String myDeparture, String myDestination, int childTicketAmount, int adultTicketAmount, int pensTicketAmount) {
        LocalDateTime now = LocalDateTime.now();
        try {
            //Using a bufferedWriter to make a new text-file with a ticket in it
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ticket.txt"));
            bufferedWriter.write("\n                         Ticket" +
                    "\n----------------------------------------------------------" +
                    "\nTime of transaction : " + dtf.format(now) +
                    "\n" + myDeparture.toUpperCase() + " --> " + myDestination.toUpperCase() +
                    "\nTotal Ticket Amount : " + totalTickets +
                    "\nChild-Tickets : " + childTicketAmount +
                    "\nAdult-Tickets : " + adultTicketAmount +
                    "\nPensioner-Tickets : " + pensTicketAmount +
                    "\nTotal price: " + sumToPay + " Kr" +
                    "\n----------------------------------------------------------");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //before we go to the main menu we make sure to print the ticket one time
        printTicket();
        menu.mainMenu();
    }

    //This method prints each line of the "ticket.txt" in the console using a bufferedReader
    public void printTicket() {
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ticket.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
