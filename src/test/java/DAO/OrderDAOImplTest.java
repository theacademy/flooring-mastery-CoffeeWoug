package DAO;
import org.junit.jupiter.api.BeforeEach;
import DTO.Order;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDAOImplTest {

    public OrderDAO testDao;

    // Create a blank test file and creating a instance of the DAO we are about to test
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "src/UserOrders/OrdersTest_";
        new FileWriter(testFile); // Quickly blank the file
        testDao = new OrderDAOImpl(testFile);
    }

    // Check to see if calculations for add are correct
    @org.junit.jupiter.api.Test
    public void testAddGetOrder() throws Exception {
        // ARRANGE - Get variables and methods calls ready
        Order expectedOrder = new Order();
        String date = "09/09/2026";
        expectedOrder.customerName = "John Cena";
        expectedOrder.state = "Washington";
        expectedOrder.taxRate = new BigDecimal("9.25");
        expectedOrder.productType = "Carpet";
        expectedOrder.area = new BigDecimal("220");
        expectedOrder.costPerSquareFoot = new BigDecimal("2.25");
        expectedOrder.laborCostPerSquareFoot = new BigDecimal("2.10");
        expectedOrder.calculateAllCosts();


        // ASSERT - Add to DAO and get from DAO
        testDao.addOrder(date, expectedOrder);
        Order resultOrder = testDao.getOrder(date, 1);

        // ASSERT - Check if data is as it should be
        assertEquals(1, resultOrder.orderNumber);
        assertEquals(expectedOrder.customerName, resultOrder.customerName);
        assertEquals(expectedOrder.state, resultOrder.state);
        assertEquals(expectedOrder.productType, resultOrder.productType);
        assertEquals(expectedOrder.area, resultOrder.area);
        assertEquals(expectedOrder.costPerSquareFoot, resultOrder.costPerSquareFoot);
        assertEquals(expectedOrder.laborCostPerSquareFoot, resultOrder.laborCostPerSquareFoot);

        // Calculations
        BigDecimal expectedMaterialCost = expectedOrder.area.multiply(expectedOrder.costPerSquareFoot);
        assertEquals(expectedMaterialCost, resultOrder.materialCost);

        BigDecimal expectedLaborCost = expectedOrder.area.multiply(expectedOrder.laborCostPerSquareFoot);
        assertEquals(expectedLaborCost, resultOrder.laborCost);

        BigDecimal expectedTax = (expectedMaterialCost.add(expectedLaborCost)).multiply((expectedOrder.taxRate.divide(BigDecimal.valueOf(100))));
        assertEquals(expectedTax,resultOrder.tax);

        BigDecimal expectedTotal = expectedMaterialCost.add(expectedLaborCost).add(expectedTax);
        assertEquals(expectedTotal, resultOrder.total);
    }

    @org.junit.jupiter.api.Test
    public void getOrders() throws Exception{
        // ARRANGE - Get variables and methods calls ready
        Order expectedOrder = new Order();
        String date = "09/09/2026";
        expectedOrder.customerName = "John Cena";
        expectedOrder.state = "Washington";
        expectedOrder.taxRate = new BigDecimal("9.25");
        expectedOrder.productType = "Carpet";
        expectedOrder.area = new BigDecimal("220");
        expectedOrder.costPerSquareFoot = new BigDecimal("2.25");
        expectedOrder.laborCostPerSquareFoot = new BigDecimal("2.10");
        expectedOrder.calculateAllCosts();

        Order expectedOrder2 = new Order();
        String secondDate = "09/09/2026";
        expectedOrder2.customerName = "Seth Rollins";
        expectedOrder2.state = "Calfornia";
        expectedOrder2.taxRate = new BigDecimal("25.00");
        expectedOrder2.productType = "Tile";
        expectedOrder2.area = new BigDecimal("230");
        expectedOrder2.costPerSquareFoot = new BigDecimal("3.50");
        expectedOrder2.laborCostPerSquareFoot = new BigDecimal("4.15");
        expectedOrder2.calculateAllCosts();

        testDao.addOrder(date, expectedOrder);
        testDao.addOrder(secondDate, expectedOrder2);

        Set<Order> orderSet = new HashSet<>();
        orderSet.add(expectedOrder);
        orderSet.add(expectedOrder2);
        List<Order> expectedOrders = new ArrayList<>(orderSet);

        List<Order> resultOrder = testDao.getOrders(date);
        testDao.addOrder(date, expectedOrder);
        testDao.addOrder(secondDate, expectedOrder2);

        assertEquals(expectedOrders, resultOrder);
    }

    @org.junit.jupiter.api.Test
    public void getOrder() throws Exception{
        // ARRANGE - Get variables and methods calls ready
        Order expectedOrder = new Order();
        String date = "09/09/2026";
        expectedOrder.customerName = "John Cena";
        expectedOrder.state = "Washington";
        expectedOrder.taxRate = new BigDecimal("9.25");
        expectedOrder.productType = "Carpet";
        expectedOrder.area = new BigDecimal("220");
        expectedOrder.costPerSquareFoot = new BigDecimal("2.25");
        expectedOrder.laborCostPerSquareFoot = new BigDecimal("2.10");
        expectedOrder.calculateAllCosts();

        // ACT
        testDao.addOrder(date, expectedOrder);
        Order resultOrder = testDao.getOrder(date, 1);
        assertEquals(expectedOrder, resultOrder);

    }

    @org.junit.jupiter.api.Test
    public void deleteOrder() throws Exception{
        // ARRANGE - Get variables and methods calls ready
        Order expectedOrder = new Order();
        String date = "09/09/2026";
        expectedOrder.customerName = "John Cena";
        expectedOrder.state = "Washington";
        expectedOrder.taxRate = new BigDecimal("9.25");
        expectedOrder.productType = "Carpet";
        expectedOrder.area = new BigDecimal("220");
        expectedOrder.costPerSquareFoot = new BigDecimal("2.25");
        expectedOrder.laborCostPerSquareFoot = new BigDecimal("2.10");
        expectedOrder.calculateAllCosts();

        Order expectedOrder2 = new Order();
        String secondDate = "09/09/2026";
        expectedOrder2.customerName = "Seth Rollins";
        expectedOrder2.state = "Calfornia";
        expectedOrder2.taxRate = new BigDecimal("25.00");
        expectedOrder2.productType = "Tile";
        expectedOrder2.area = new BigDecimal("230");
        expectedOrder2.costPerSquareFoot = new BigDecimal("3.50");
        expectedOrder2.laborCostPerSquareFoot = new BigDecimal("4.15");
        expectedOrder2.calculateAllCosts();

        testDao.addOrder(date, expectedOrder);
        testDao.addOrder(secondDate, expectedOrder2);
        testDao.deleteOrder(date, 2);

        List<Order> orders = testDao.getOrders(date);
        assertEquals(1, orders.size());

    }
}