package org.atm.serialization;

import com.sun.source.tree.CaseTree;
import org.atm.module.CreditCard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CreditCardSerializer {

    static Path path = Path.of("CreditCards.txt");

    //Map with creditCardNumber (key) & creditCard (value)
    public static Map<String, CreditCard> mapNumberToCreditCard = new HashMap<>();


    public List<String> saveCreditCards(List<CreditCard> creditCardList){
        List<String> lines = new ArrayList<>();
        for (var creditCard : creditCardList){
            String line =  "CreditCard_Number: " + creditCard.number() + "\n" +
                    "Pin: " + creditCard.pin() + "\n" +
                    "Balance: " + creditCard.balance() + "\n" +
                    "Active: " + creditCard.active() + "\n" +
                    "Owner: "  + creditCard.owner() + "\n";
            lines.add(line);

        }
        return lines;
    }


    public List<CreditCard> parseCreditCards(){
        List<String> lines = getAllLines();
        lines.removeAll(Collections.singleton(""));
        List<CreditCard> creditCardList = new ArrayList<>();
        for (int linesPassed = 0; lines.size() > linesPassed; linesPassed += 5){
            var number = getNumber(lines.get(linesPassed));
            var pin = getPin(lines.get(linesPassed + 1));
            var balance = getBalance(lines.get(linesPassed + 2));
            var active = getActive(lines.get(linesPassed + 3));
            var owner = getOwner(lines.get(linesPassed + 4));
            CreditCard creditCard = new CreditCard(number, pin, balance, active, owner);
            creditCardList.add(creditCard);
            mapNumberToCreditCard.put(creditCard.number(), creditCard);
        }
        return creditCardList;
    }


            //find creditCard by Number method
    public static List<CreditCard> findCreditCardsByNumber(String line){
        List<CreditCard> creditCards = new ArrayList<>();
        var numbers = line.split(", ");
        for(var number : numbers){
            creditCards.add(mapNumberToCreditCard.get(number));
        } return creditCards;
    }

    public static List<CreditCard> findCreditCardsByNumber(List<String> numbers) {
        List<CreditCard> creditCards = new ArrayList<>();
        for (var number : numbers) {
            creditCards.add(mapNumberToCreditCard.get(number));
        }
        return creditCards;
    }


    protected void setPah(String newPath){
        path = Path.of(newPath);
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
