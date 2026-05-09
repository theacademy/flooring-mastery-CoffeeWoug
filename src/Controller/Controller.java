package Controller;

import DAO.FlooringPersistenceException;
import DTO.Order;
import DTO.Product;
import DTO.Taxes;
import Service.FlooringDataValidationException;
import Service.FlooringServiceLayer;
import Service.FlooringServiceLayerImpl;

import UI.FlooringView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Controller {

    public FlooringView view = new FlooringView();
    public FlooringServiceLayer service = new FlooringServiceLayerImpl();

    public void run() {

        boolean running = true;

        while(running) {
            int menuSelection = getMenuSelection();
            switch(menuSelection) {
                case 1:
                    getOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    System.out.println("Edit an Order");
                    break;
                case 4:
                    System.out.println("Remove an Order");
                    break;
                case 5:
                    System.out.println("Export All Data");
                    break;
                case 6:
                    System.out.println("Quit");
                    running = false;
                    break;
            }
        }
    }

    public int getMenuSelection() {
        return view.printMenuSelection();
    }


    public void addOrder() {
        view.addOrderBanner();
        boolean hasErrors = false;

        do {
            // Asking user for Date, Customer Name and US State
            String userDate = view.getDateFromUser();
            String userCustomerName = view.getCustomerNameFromUser();
            String userState = view.getStateFromUser();
            view.displaySpace();
            try {
                // Validating the Date, Customer Name and US State
                LocalDate validatedDate = validateDate(userDate);
                String validatedCustomerName = validateName(userCustomerName);
                String validatedState = validateState(userState);
                BigDecimal validatedStateTaxRate = validateStateTaxRate(validatedState);

                // Validate product selection, and area
                view.productSelection(service.getProducts());
                String userProduct = view.getProductFromUser();
                Product validatedProduct = service.validateProduct(userProduct, service.getProducts());
                String userArea = view.getAreaFromUser();
                BigDecimal validatedArea = validateArea(userArea);

                // Creating order to add to in program memory
                Order order = new Order();
                order.orderDate = validatedDate;
                order.customerName = validatedCustomerName;
                order.state = validatedState;
                order.taxRate = validatedStateTaxRate;
                order.productType = validatedProduct.productType;
                order.area = validatedArea;
                order.costPerSquareFoot = validatedProduct.costPerSquareFoot;
                order.laborCostPerSquareFoot = validatedProduct.laborCostPerSquareFoot;
                order.calculateAllCosts();

                service.addOrder(userDate, order);
                hasErrors = false;

            } catch (FlooringDataValidationException | FlooringPersistenceException e) {
                view.displayErrorMessages(e.getMessage() + " try again");
                hasErrors = true;
            }
        } while(hasErrors);



    }

    public void getOrders() {
        try {
            String userDate = view.getDateFromUser();
            LocalDate validatedDate = service.validateDate(userDate);
            List<Order> orderList = service.getOrders(userDate);
            view.displayOrders(orderList);
        } catch(FlooringDataValidationException | FlooringPersistenceException e) {
            view.displayErrorMessages(e.getMessage() + " try again");
        }
    }

    public void editOrder() {

    }

    public void removeOrder() {

    }

    public void exportData() {

    }

    public void unknownCommand() {

    }

    public void exitMessage() {

    }

    public LocalDate validateDate(String date) throws FlooringDataValidationException {
        return service.validateDate(date);
    }

    public String validateName(String userName) throws FlooringDataValidationException {
        return service.validateName(userName);
    }

    public String validateState(String state) throws FlooringPersistenceException, FlooringDataValidationException {
        return service.validateState(state);
    }

    // Different to the UML
    public BigDecimal validateStateTaxRate(String state) throws FlooringPersistenceException, FlooringDataValidationException{
        Taxes taxObj = service.getStateInfo(state);
        return taxObj.taxRate;
    }

    public BigDecimal validateArea(String userArea) throws FlooringDataValidationException{
        return service.validateArea(userArea);
    }
}

/*
System.out.println("Valid date " + validatedDate);
System.out.println("Valid name " + validatedCustomerName);
System.out.println("Valid state " + validatedState);
System.out.println("Valid state tax rate " + validateStateTaxRate(validatedState));
 */
