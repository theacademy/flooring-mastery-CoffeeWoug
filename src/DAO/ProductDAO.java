package DAO;

import DTO.Product;

import java.util.List;
import java.util.ArrayList;

public interface ProductDAO {
    // public Product getProductInfo();
    public List<Product> getProductList() throws FlooringPersistenceException;
}
