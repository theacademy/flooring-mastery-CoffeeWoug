package Service;

import DAO.*;
import DTO.Order;
import DTO.Product;
import DTO.Taxes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.ArrayList;

public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    public TaxesDao taxesDao = new TaxesDaoImpl();
    public ProductDAO productDAO = new ProductDaoImpl();
    public OrderDAO orderDAO = new OrderDAOImpl();

    public void addOrder(String date, Order order) throws FlooringPersistenceException{
        orderDAO.addOrder(date, order);
    }

    // getStateInfo returns the whole TaxObject (Should really be called getTaxesInfo
    public Taxes getStateInfo(String state) throws FlooringPersistenceException, FlooringDataValidationException{
        return taxesDao.getTaxesInfo(state);
    }

    public List<Product> getProducts() throws FlooringPersistenceException{
        return productDAO.getProductList();
    }

    // --- VALIDATION METHODS ---
    public LocalDate validateDate(String date) throws FlooringDataValidationException {
        LocalDate dateToCheck;
        try {
            dateToCheck = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            if (dateToCheck.isBefore(LocalDate.now())) {
                throw new FlooringDataValidationException("Date is set in the past");
            }
        } catch (DateTimeParseException e) {
            throw new FlooringDataValidationException("Date format was incorrect");
        }
        return dateToCheck;
    }

    public String validateName(String userName) throws FlooringDataValidationException {
        // Check for blanks or whitespace
        if(userName == null || userName.trim().length() == 0) {
            throw new FlooringDataValidationException("Customer name provided is null or blank");
        } else {
            // Checking for special characters
            for(char c : userName.toCharArray()) {
                if(!Character.isLetterOrDigit(c) && c != '.' && c != ',' && c != ' ') {
                    throw new FlooringDataValidationException("Customer name provided has special characters");
                }
            }
        }
        return userName;
    }

    public Product validateProduct(String product, List<Product> products) throws FlooringDataValidationException{
        for(Product p : products) {
            if(p.productType.equals(product)) {
                return p;
            }
        }
        throw new FlooringDataValidationException("Product not found");
    }

    // Returns the state name
    public String validateState(String state) throws FlooringPersistenceException, FlooringDataValidationException{
        return taxesDao.getTaxesInfo(state).stateName;
    }

    public BigDecimal validateArea(String userArea) throws FlooringDataValidationException{
        try {
            BigDecimal area = new BigDecimal(userArea);
            BigDecimal minimumArea = new BigDecimal("100");
            if(area.compareTo(minimumArea) < 0) {
                throw new FlooringDataValidationException("Area too small");
            }
            return area;
        } catch (NumberFormatException e) {
            throw new FlooringDataValidationException("Not an valid number for Area");
        }
    }


}
