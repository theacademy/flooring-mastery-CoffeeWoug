package DAO;

import DTO.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderDAO {
    public void addOrder(String date, Order Order) throws FlooringPersistenceException;
    public List<Order> getOrders(String date)throws FlooringPersistenceException;
}
