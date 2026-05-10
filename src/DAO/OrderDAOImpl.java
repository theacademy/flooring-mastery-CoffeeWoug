package DAO;

import DTO.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class OrderDAOImpl implements OrderDAO{

    public final String FILE_PATH = "src/UserOrders/Orders_";
    public static final String DELIMITER = "::";
    public Map<String, Set<Order>> orderMap = new HashMap<>();

    public String marshallOrder(Order order) {
        String orderAsText = order.orderNumber + DELIMITER;
        orderAsText += order.customerName + DELIMITER;
        orderAsText += order.state + DELIMITER;
        orderAsText += order.taxRate + DELIMITER;
        orderAsText += order.productType + DELIMITER;
        orderAsText += order.area + DELIMITER;
        orderAsText += order.costPerSquareFoot + DELIMITER;
        orderAsText += order.laborCostPerSquareFoot + DELIMITER;
        orderAsText += order.materialCost + DELIMITER;
        orderAsText += order.laborCost + DELIMITER;
        orderAsText += order.tax + DELIMITER;
        orderAsText += order.total;
        return orderAsText;
    }

    public void writeOrder(String date) throws FlooringPersistenceException{
        String dateAsString = "";
        for(char c : date.toCharArray()) {
            if(c != '/') dateAsString += c;
        }

        PrintWriter out;

        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH + dateAsString);
            out = new PrintWriter(fileWriter);
        } catch(IOException e) {
            throw new FlooringPersistenceException("Could not save data");
        }

        Set<Order> orderSet = orderMap.get(date);

        for(Order o : orderSet) {
            String marshalledOrder = marshallOrder(o);
            out.println(marshalledOrder);
            out.flush();
        }

        out.close();
    }

    public void addOrder(String date, Order order) throws FlooringPersistenceException{
        // Put object into program memory
        // Put program memory into secondary storage
        if(orderMap.containsKey(date)) {
            Set<Order> orderSet = orderMap.get(date);
            order.orderNumber = orderSet.size() + 1;
            orderSet.add(order);
            orderMap.put(date, orderSet);
        } else {
            Set<Order> orderSet = new HashSet<>();
            order.orderNumber = 1;
            orderSet.add(order);
            orderMap.put(date, orderSet);
        }
        this.writeOrder(date);
    }

    public void editOrder(String date, Order order) throws FlooringPersistenceException{
        this.writeOrder(date);
    }

    public List<Order> getOrders(String date) throws FlooringPersistenceException{
        if(!orderMap.isEmpty() && !date.isEmpty() && orderMap.containsKey(date)) {
            Set<Order> orderSet = orderMap.get(date);
            List<Order> orderList = new ArrayList<>(orderSet);
            return orderList;
        } else {
            throw new FlooringPersistenceException("No orders for this date");
        }
    }

    public Order getOrder(String date, int orderNumber) throws FlooringPersistenceException{
        Set<Order> orderSet = orderMap.get(date);
        if(orderMap.containsKey(date)) {
            for(Order o : orderSet) {
                if(o.orderNumber == orderNumber) {
                    return o;
                }
            }
            throw new FlooringPersistenceException("No Order found for this date and number");
        }
        throw new FlooringPersistenceException("No Order found for this date and number");
    }

    public void deleteOrder(String date, int orderNumber) throws FlooringPersistenceException {
        Set<Order> orderSet = orderMap.get(date);
        for(Order o : orderSet) {
            if(o.orderNumber == orderNumber) {
                orderSet.remove(o);
                break;
            }
        }
        orderMap.put(date, orderSet);
        this.writeOrder(date);
    }

    public void exportOrders() throws FlooringPersistenceException {
        PrintWriter out;

        try {
            FileWriter fileWriter = new FileWriter("/home/mihirmistry/Downloads/Orders");
            out = new PrintWriter(fileWriter);
        } catch (IOException e) {
            throw new FlooringPersistenceException("Error when creating an export file");
        }

        if(!orderMap.isEmpty()) {
            for(Set<Order> set : orderMap.values()) {
                for(Order o : set) {
                    String marshalledOrder = marshallOrder(o);
                    out.println(marshalledOrder);
                    out.flush();
                }

            }
        }
        out.close();
    }
 }
