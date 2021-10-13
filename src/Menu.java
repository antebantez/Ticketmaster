import java.util.Scanner;

public class Menu {
    Scanner myScanner = new Scanner(System.in);
    TicketMachine ticketMachine = new TicketMachine();
    int menuChoice;

    public void mainMenu() {

        System.out.println("-*--*--*--*--*--*--*--*-");
        System.out.println(" ");
        System.out.println("1. Browse trips");
        System.out.println("2. Show all available routes");
        System.out.println("3. Visa mina biljetter");
        System.out.println("4. Visa biljettpriser");
        System.out.println();
        try {
            menuChoice = myScanner.nextInt();

        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (menuChoice) {
            case 1:
                ticketMachine.printDepartureOptions();
                break;
            case 2:
                ticketMachine.travelRoutes();

                break;

            case 3:
                System.out.println("Hej 3");
                break;

            default:
                System.out.println("Sorry! Something went wrong!\n" +
                        "Please enter a valid choice");
                break;
        }

    }
}
