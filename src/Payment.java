import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Payment {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    Scanner myScanner = new Scanner(System.in);
    TicketMachine ticketMachine = new TicketMachine();
    MenuClass menu = new MenuClass();

    int money = 0;
    int kidPrice = 45;
    int pensionerPrice = 50;
    int adultPrice = 60;

    int totalTickets = 0;
    int sumToPay = 0;


    public void payForTickets() {

        System.out.println(ticketMachine.getMyDepartureString());

        totalTickets = ticketMachine.getChildTicketAmount() + ticketMachine.getAdultTicketAmount() + ticketMachine.getPensionerTicketAmount();

        sumToPay = (ticketMachine.getChildTicketAmount() * kidPrice) + (ticketMachine.getAdultTicketAmount() * adultPrice) +
                (ticketMachine.getPensionerTicketAmount() * pensionerPrice);


        if (ticketMachine.getMyDepartureString().equalsIgnoreCase("Köpenhamn C") ||
                ticketMachine.getMyDestinationString().equalsIgnoreCase("Köpenhamn C")) {
            sumToPay += (5 * totalTickets);
        }
        System.out.println("Amount to pay : " + (sumToPay - money));
        System.out.println("Please insert money into the machine");
        money += myScanner.nextInt();
        if (money >= sumToPay) {
            System.out.println("Payment succeeded!");
            System.out.println("You get " + (money - sumToPay) + " in return!");
            System.out.println("Printing ticket...");
            makeTicket();

        } else {
            System.out.println("You cant afford the tickets, please insert another " +
                    (sumToPay - money) + " Kr");
            payForTickets();
        }
    }


    public void makeTicket() {
        LocalDateTime now = LocalDateTime.now();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ticket.txt"));
            bufferedWriter.write("                         Ticket\n" +
                    "----------------------------------------------------------" +
                    "\nTime of purchase : " + dtf.format(now) +
                    "\nTotal tickets bought : " + totalTickets +
                    "\nChild-Tickets : " + ticketMachine.getChildTicketAmount() +
                    "\nAdult-Tickets : " + ticketMachine.getAdultTicketAmount() +
                    "\nPensioner-Tickets : " + ticketMachine.getPensionerTicketAmount() +
                    "\nTotal price: " + sumToPay + " Kr" +
                    "\n----------------------------------------------------------");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printTicket();
        menu.mainMenu();
    }

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
