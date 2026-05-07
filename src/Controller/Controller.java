package Controller;

import UI.FlooringView;
import UI.UserIO;
import UI.UserIOConsoleImpl;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InputMismatchException;

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
                    addOrder();
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
        LocalDate userDate = validateDate();
    }

    // CHECKS IF DATE IS VALID FORMAT AND IS AFTER TODAY
    public LocalDate validateDate() {
        boolean dateValidated = false;
        LocalDate todayDate = LocalDate.now();

        while(!dateValidated) {
            // Get String date from User
            try {
                String userDate = view.getDateFromUser();
                LocalDate ld = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                if (ld.isAfter(todayDate)) {
                    System.out.println("Date is after");
                    dateValidated = true;
                } else if (ld.isBefore(todayDate)) {
                    System.out.println("Date is before");
                }
            } catch(InputMismatchException | DateTimeException e) {
                io.print("Not valid date");
            }
        }
        return LocalDate.now();
    }
}
