package Service;

import DAO.FlooringDAOException;
import DAO.FlooringPersistenceException;
import DTO.Order;
import DTO.Product;
import DTO.Taxes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FlooringServiceLayer {
    public void addOrder(String date, Order order) throws FlooringPersistenceException;
    public List<Order> getOrders(String date) throws FlooringPersistenceException;
    public Taxes getStateInfo(String state) throws FlooringPersistenceException, FlooringDataValidationException;
    public LocalDate validateDate(String date) throws FlooringDataValidationException;
    public String validateName(String userName) throws FlooringDataValidationException;
    public String validateState(String state) throws FlooringPersistenceException, FlooringDataValidationException;
    public List<Product> getProducts() throws FlooringPersistenceException;
    public Product validateProduct(String product, List<Product> products) throws FlooringDataValidationException;
    public BigDecimal validateArea(String userArea) throws FlooringDataValidationException;
}
