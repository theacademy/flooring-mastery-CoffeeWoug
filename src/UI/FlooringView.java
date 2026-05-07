package UI;
import java.util.Scanner;

public class FlooringView {

    private Scanner scanner = new Scanner(System.in);
    UserIO io = new UserIOConsoleImpl();

    public int printMenuSelection() {
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");
        int userMenuSelection = io.readInt("*** PLEASE ENTER MENU SELECTION ***");
        return userMenuSelection;
    }

    public String getDateFromUser() {
        String userDate = io.readString("Please enter a date, format is MM/DD/YYYY");
        return userDate;
    }
}
