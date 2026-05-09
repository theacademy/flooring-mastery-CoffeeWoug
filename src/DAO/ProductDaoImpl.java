package DAO;
import DTO.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDAO {

    private final String FILEPATH = "src/Products.txt";
    public List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() throws FlooringPersistenceException{
        if(productList.isEmpty()) load();
        return this.productList;
    }

    public Product unmarshallProducts(String productsAsText) {
        String[] txtLine = productsAsText.split(",");
        Product product = new Product();
        product.productType = txtLine[0];
        product.costPerSquareFoot = new BigDecimal(txtLine[1]);
        product.laborCostPerSquareFoot = new BigDecimal(txtLine[2]);
        return product;
    }

    public void load() throws FlooringPersistenceException {
        try {
            FileReader fileReader = new FileReader(FILEPATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner scanner = new Scanner(bufferedReader);
            scanner.nextLine();

            while(scanner.hasNextLine()) {
                Product productFromFile = unmarshallProducts(scanner.nextLine());
                productList.add(productFromFile);
            }
        } catch(FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not fetch products");
        }
    }
}
