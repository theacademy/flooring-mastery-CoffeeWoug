package UI;

import DTO.Order;
import DTO.Product;

import java.util.List;
import java.util.Objects;

public class FlooringView {

    public UserIO io = new UserIOConsoleImpl();

    public int printMenuSelection() {
        displayStars();
        io.print("<<FLOORING PROGRAM>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");

        return io.readInt("To get started, select a menu option");
    }

    public void productSelection(List<Product> products) {
        for(Product product : products) {
            io.print("Product type " + product.productType);
            io.print("Product cost per Square foot " + product.costPerSquareFoot);
            io.print("Product labor cost per square foot " + product.laborCostPerSquareFoot);
            displayDashes();
        }
    }

    public String getDateFromUser() {
        return io.readString("Enter Order date");
    }

    public String getCustomerNameFromUser() {
        return io.readString("Enter Customer Name");
    }

    public String getStateFromUser() {
        return io.readString("Enter a American State");
    }

    public String getProductFromUser() {
        return io.readString("Please enter from one of the following products");
    }

    public String getAreaFromUser() {
        return io.readString("Please enter the area for your flooring option");
    }

    public void displayOrders(List<Order> orders) {
        displayDashes();
        for(Order o : orders) {
            io.print(o.toString());
        }
    }

    public void displayErrorMessages(String errorMessage) {
        io.print("=== ERROR ===");
        io.print(errorMessage);
    }

    // *** BANNERS ***

    public void displayStars() {
        io.print("****************************************");
    }

    public void displayDashes() {
        io.print("----------------------------------------");
    }

    public void displaySpace() {
        io.print("");
    }

    public void addOrderBanner() {
        displayStars();
        io.print("Creating Order");
    }
}
