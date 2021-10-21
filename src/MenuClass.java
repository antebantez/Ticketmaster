import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuClass {
    Scanner myScanner = new Scanner(System.in);
    //this TicketMachine-object makes me eligible to use the methods from that class.
    TicketMachine ticketMachine = new TicketMachine();
    //a variable to keep track on the menu-choice that the user will make
    int menuChoice;

    //This is the main menu, the user will  get 5 different options to choose from
    public void mainMenu() {
        do {
            Payment payment = new Payment();
            System.out.println("-*--*--*--*--*--*--*--*-");
            System.out.println(" ");
            System.out.println("1. Browse trips");
            System.out.println("2. Show all available routes");
            System.out.println("3. Show my tickets");
            System.out.println("4. Show ticket prices");
            System.out.println("5. Exit ticket machine");
            System.out.println(" ");
            System.out.println("-*--*--*--*--*--*--*--*-");
            try {
                //We let the user write their menu-choice and run the code in the switch case that it's equal to
                menuChoice = myScanner.nextInt();
                switch (menuChoice) {
                    case 1:
                        //Starts the method for a ticket-purchase
                        ticketMachine.printDepartureOptions();
                        break;
                    case 2:
                        //Shows all possible routes that you can take
                        ticketMachine.travelRoutes();
                        break;
                    case 3:
                        //Prints the latest ticket bought in the ticket-machine
                        payment.printTicket();
                        mainMenu();
                        break;
                    case 4:
                        //Prints all the ticket-prices and notifies the user that a ticket to/from Köpenhamn C
                        //will cost an extra 5 kr per ticket
                        System.out.println("Child-Ticket : " + payment.kidPrice);
                        System.out.println("Adult-Ticket : " + payment.adultPrice);
                        System.out.println("Pensioner-Price : " + payment.pensionerPrice);
                        System.out.println("""
                                FOR YOUR INFORMATION
                                --------------------
                                A trip from/to Köpenhamn C will cost 5 kr extra per ticket!!!""");
                        mainMenu();
                        break;
                    case 5:
                        //Exits the program
                        System.exit(0);
                        break;
                    //if the menu-choice isn't between the number 1-5 then the program will send the user to the-
                    //beginning of the menu to try again
                    default:
                        System.out.println("Invalid option, please press a number from 1 - 5 only!");
                        mainMenu();
                }
                //this catches the exception if the user writes in
                // anything else than a number, which would have caused an error
            } catch (InputMismatchException e) {
                String clearScanner = myScanner.next();
                System.out.println("Invalid input, Please try again!");
                mainMenu();

            }
            //the condition will be true as long as the menuChoice is not equal to 5
        } while (menuChoice != 5);
    }
}


