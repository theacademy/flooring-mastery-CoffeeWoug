package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Order {
    public Integer orderNumber;
    public LocalDate orderDate;
    public String customerName;
    public String state;
    public BigDecimal taxRate;
    public String productType;
    public BigDecimal area;
    public BigDecimal costPerSquareFoot;
    public BigDecimal laborCostPerSquareFoot;
    public BigDecimal materialCost;
    public BigDecimal laborCost;
    public BigDecimal tax;
    public BigDecimal total;

    public void calculateAllCosts() {
        this.materialCost = this.area.multiply(this.costPerSquareFoot);
        this.laborCost = this.area.multiply(this.laborCostPerSquareFoot);
        this.tax = (this.materialCost.add(laborCost)).multiply(this.taxRate.divide(BigDecimal.valueOf(100)));
        this.total = this.materialCost.add(this.laborCost).add(this.tax);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderNumber, order.orderNumber) && Objects.equals(orderDate, order.orderDate) && Objects.equals(customerName, order.customerName) && Objects.equals(state, order.state) && Objects.equals(taxRate, order.taxRate) && Objects.equals(productType, order.productType) && Objects.equals(area, order.area) && Objects.equals(costPerSquareFoot, order.costPerSquareFoot) && Objects.equals(laborCostPerSquareFoot, order.laborCostPerSquareFoot) && Objects.equals(materialCost, order.materialCost) && Objects.equals(laborCost, order.laborCost) && Objects.equals(tax, order.tax) && Objects.equals(total, order.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, orderDate, customerName, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", orderDate=" + orderDate +
                ", customerName='" + customerName + '\'' +
                ", state='" + state + '\'' +
                ", taxRate=" + taxRate +
                ", productType='" + productType + '\'' +
                ", area=" + area +
                ", costPerSquareFoot=" + costPerSquareFoot +
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot +
                ", materialCost=" + materialCost +
                ", laborCost=" + laborCost +
                ", tax=" + tax +
                ", total=" + total +
                '}';
    }
}
