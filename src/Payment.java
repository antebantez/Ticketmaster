import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Payment {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    MenuClass menu = new MenuClass();

    int money = 0;
    int kidPrice = 45;
    int pensionerPrice = 50;
    int adultPrice = 60;
    int totalTickets = 0;
    int sumToPay = 0;
    public void payForTickets(String myDep, String myDes, int childT, int adultT, int pensT) {
        totalTickets = childT + adultT + pensT;

        sumToPay = (childT * kidPrice) + (adultT * adultPrice) +
                (pensT * pensionerPrice);


        if (myDep.equalsIgnoreCase("Köpenhamn C") ||
                myDes.equalsIgnoreCase("Köpenhamn C")) {
            sumToPay += (5 * totalTickets);
        }
        System.out.println("Amount to pay : " + (sumToPay - money));
        System.out.println("Please insert money into the machine");
        Scanner myScanner = new Scanner(System.in);
        money += myScanner.nextInt();
        if (money >= sumToPay) {
            System.out.println("Payment succeeded!");
            System.out.println("You get " + (money - sumToPay) + " in return!");
            System.out.println("Printing ticket...");
            makeTicket(myDep, myDes, childT, adultT,pensT);

        } else {
            System.out.println("You cant afford the tickets, please insert another " +
                    (sumToPay - money) + " Kr");
            payForTickets(myDep, myDes, childT, adultT,pensT);
        }
    }


    public void makeTicket(String myDep, String myDes, int childT, int adultT, int pensT) {


        LocalDateTime now = LocalDateTime.now();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("ticket.txt"));
            bufferedWriter.write("                         Ticket\n" +
                    "----------------------------------------------------------" +
                    "\nTime of transaction : " + dtf.format(now) +
                    "\n" + myDep.toUpperCase()+ " --> " + myDes.toUpperCase() +
                    "\nTotal Ticket Amount : " + totalTickets +
                    "\nChild-Tickets : " + childT +
                    "\nAdult-Tickets : " + adultT +
                    "\nPensioner-Tickets : " + pensT +
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
