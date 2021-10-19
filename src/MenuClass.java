import java.util.Scanner;

public class MenuClass {
    Scanner myScanner = new Scanner(System.in);
    TicketMachine ticketMachine = new TicketMachine();
    int menuChoice;

    public void mainMenu() {

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
            menuChoice = myScanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            mainMenu();
        }

        switch (menuChoice) {
            case 1:
                ticketMachine.printDepartureOptions();
                break;
            case 2:
                ticketMachine.travelRoutes();

                break;

            case 3:
                payment.printTicket();
                mainMenu();
                break;
            case 4:
                System.out.println("Child-Ticket : " + payment.kidPrice);
                System.out.println("Adult-Ticket : " + payment.adultPrice);
                System.out.println("Pensioner-Price : " + payment.pensionerPrice);
                System.out.println("FYI, a trip to/from Köpenhamn C will cost 5 kr extra per ticket!!!");
                mainMenu();
                break;
            case 5 :
                System.exit(0);
                break;
                default:
                System.out.println("Sorry! Something went wrong!\n" +
                        "Please enter a valid choice");
                mainMenu();
                break;
        }

    }



}
