package Controller;

import UI.FlooringView;
import UI.UserIO;
import UI.UserIOConsoleImpl;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {

    public Controller() {
        // Need to implement DI
    }

    FlooringView view = new FlooringView();
    UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean running = true;

        while(running) {
            int userMenuSelection = getMenuSelection();
            switch (userMenuSelection) {
                case 1:
                    io.print("Display Orders");
                    break;
                case 2:

                    break;
                case 3:
                    io.print("Edit an order");
                    break;
                case 4:
                    io.print("Remove an order");
                    break;
                case 5:
                    io.print("Export all data");
                    break;
                case 6:
                    io.print("Quit");
                    running = false;
                    break;
                default:
                    io.print("7. Menu option not valid");
            }
        }
    }

    public int getMenuSelection() {
        return view.printMenuSelection();
    }

    public void addOrder() {
        String userDate = view.getDateFromUser();
        LocalDate ld = null;
        try {
            validateDate(userDate);
        } catch(DateTimeException dte) {
            io.print("Not a valid date, please try again");
        }
    }

    public LocalDate validateDate(String date) {
        LocalDate todayDate = LocalDate.now();
        LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld);
        return LocalDate.now();
    }
}
