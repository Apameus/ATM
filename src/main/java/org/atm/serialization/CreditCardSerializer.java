package org.atm.serialization;

import com.sun.source.tree.CaseTree;
import org.atm.module.CreditCard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreditCardSerializer {

    static Path path = Path.of("C:\\Users\\Ιωάννης Τζωρτζίνης\\Documents\\Java\\ATM\\CreditCards.txt");


    public List<String> saveCreditCards(List<CreditCard> creditCardList){
        List<String> lines = new ArrayList<>();
        for (var creditCard : creditCardList){
            String line =  creditCard.number() + "\n" + "PIN: " + creditCard.pin() + "\n"
                        + creditCard.balance() + "$" + "\n" + creditCard.active() + "\n"
                        + creditCard.owner() + "\n";
            lines.add(line);
        }
        return lines;
    }


    public List<CreditCard> parseCreditCards(){
        List<String> lines = getAllLines();
        lines.removeAll(Collections.singleton(""));
        List<CreditCard> creditCardList = new ArrayList<>();
        var linesPassed = 0;
        while (lines.get(linesPassed).contains("CreditCard_Number:")){
            //String[] attributes = line.split("\n");
            var number = getNumber(lines.get(linesPassed));
            var pin = getPin(lines.get(linesPassed + 1));
            var balance = getBalance(lines.get(linesPassed + 2));
            var active = getActive(lines.get(linesPassed + 3));
            var owner = getOwner(lines.get(linesPassed + 4));
            linesPassed += 5;
            CreditCard creditCard = new CreditCard(number, pin, balance, active, owner);
            creditCardList.add(creditCard);
        }
        return creditCardList;
    }

    private String getOwner(String line) {
        if (line.startsWith("Owner:")){
            var lineOf = line.split(" ");
            var owner = (lineOf[1] + " " + lineOf[2]);
            return owner;
        }
        throw new IllegalArgumentException("Something went wrong in getNumber method");
    }

    private Boolean getActive(String line) {
        if (line.startsWith("Active:")){
            var lineOf = line.split(" ");
            var active = Boolean.parseBoolean(lineOf[1]);
            return active;
        }
        throw new IllegalArgumentException("Something went wrong in getNumber method");
    }

    private Double getBalance(String line) {
        if (line.startsWith("Balance:")){
            var lineOf = line.split(" ");
            var balance = Double.parseDouble(lineOf[1]);
            return balance;
        }
        throw new IllegalArgumentException("Something went wrong in getNumber method");
    }

    private String getNumber(String line) {
        if (line.startsWith("CreditCard_Number:")){
            var lineOf = line.split(" ");
            var number = lineOf[1];
            return number;
        }
        throw new IllegalArgumentException("Something went wrong in getNumber method");
    }


    private static String getPin(String line) {
        if (line.startsWith("Pin:")){
            var lineOf = line.split(" ");
            var pin = lineOf[1];
            return pin;
        }
        throw new IllegalArgumentException("Something went wrong in getPin method");
    }

    private List<String> getAllLines() {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void saveToFile(List<String> lines){
        try {
            Files.write(path,lines);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
