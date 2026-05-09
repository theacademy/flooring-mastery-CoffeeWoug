package DAO;

import DTO.Taxes;
import Service.FlooringDataValidationException;

public interface TaxesDao {
    public Taxes getTaxesInfo(String state) throws FlooringPersistenceException, FlooringDataValidationException;
    public Taxes unmarshallTaxes(String taxesAsText);
    public void loadTaxes() throws FlooringPersistenceException;
}
