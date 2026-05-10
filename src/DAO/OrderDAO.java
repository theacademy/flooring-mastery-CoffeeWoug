package DAO;

import DTO.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderDAO {
    public void addOrder(String date, Order Order) throws FlooringPersistenceException;
    public List<Order> getOrders(String date)throws FlooringPersistenceException;
   public Order getOrder(String date, int orderNumber) throws FlooringPersistenceException;
    public void editOrder(String date, Order order) throws FlooringPersistenceException;

}
