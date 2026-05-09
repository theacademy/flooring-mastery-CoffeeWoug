package DAO;

import DTO.Order;

import java.time.LocalDate;

public interface OrderDAO {
    public void addOrder(String date, Order Order) throws FlooringPersistenceException;
}
