package DAO;

import DTO.Taxes;
import Service.FlooringDataValidationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaxesDaoImpl implements TaxesDao{

    public List<Taxes> taxesList = new ArrayList<>();

    public Taxes getTaxesInfo(String state) throws FlooringPersistenceException, FlooringDataValidationException{
        loadTaxes();
        for(Taxes t : taxesList) {
            if(t.stateName.equals(state)) {
                return t;
            }
        }
        throw new FlooringDataValidationException("State was not found");
    }

    // Returns a tax Object from the given txt line
    public Taxes unmarshallTaxes(String taxesAsText) {
        String[] taxesLine = taxesAsText.split(",");
        Taxes tax = new Taxes();
        tax.state = taxesLine[0];
        tax.stateName = taxesLine[1];
        tax.taxRate = new BigDecimal(taxesLine[2]);
        return tax;
    }

    public void loadTaxes() throws FlooringPersistenceException{
        try {
            FileReader fileReader = new FileReader("src/Taxes.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner scanner = new Scanner(bufferedReader);
            scanner.nextLine();

            while(scanner.hasNextLine()) {
                Taxes taxObj = unmarshallTaxes(scanner.nextLine());
                taxesList.add(taxObj);
            }

        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("File was not found");
        }
    }




}
