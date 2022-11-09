package org.atm.serialization;

import org.atm.module.Account;
import org.atm.module.CreditCard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

//Map with creditCardNumber (key) & creditCard (value)
import static org.atm.serialization.CreditCardSerializer.mapNumberToCreditCard;

public class AccountSerializer {

    static Path path = Path.of("Accounts.txt");

                // Serializer
//    public void saveAccounts(List<Account> accountList){
//        List<String> lines = new ArrayList<>();
//        for (var account : accountList){
//            String line = "Owner: " + account.owner() + "\n" +
//                            "CC: " + getCreditCardNumbers(account.creditCardsNumbers()) + "\n" +
//                            "Balance: " + account.balance() + "\n" +
//                            "Username: " + account.username() + "\n" +
//                            "Password: " + account.password() + "\n" + "\n" ;
//
//            lines.add(line);
//        }
//        saveToFile(lines);
//    }

    public void saveAccounts(List<Account> accountList){
        //final ArrayList with String's
        List<String> lines = new ArrayList<>();
        //secondary ArrayLst with stringBuilder's
        List<StringBuilder> stringBuilders = new ArrayList<>();
        var x = 0;
        for (var account : accountList){
            StringBuilder line = new StringBuilder();
            line.append("Owner: ").append(account.owner()).append("\n")
                    .append("CC: ").append(getCreditCardNumbers(account.creditCardsNumbers())).append("\n")
                    .append("Balance: ").append(account.balance()).append("\n")
                    .append("Username: ").append(account.username()).append("\n")
                    .append("Password: ").append(account.password()).append("\n").append("\n");
            stringBuilders.add(line);
              //error here!
            x ++;
        }
        //  Deleting the last two "\n" from the last strBuilder
        stringBuilders.get(x - 1).deleteCharAt(stringBuilders.get(x - 1).lastIndexOf("\n"));
        stringBuilders.get(x - 1).deleteCharAt(stringBuilders.get(x - 1).lastIndexOf("\n"));
        //  Converting List<StringBuilder> to List<Sting>
        for (var e : stringBuilders){
            lines.add(e.toString());
        }
        // Return what's needed
        saveToFile(lines);
    }

    //Parser

    public List<Account> parseAccounts(){
        List<String> lines = getAllLines();
        lines.removeAll(Collections.singleton(""));
        List<Account> accountList = new ArrayList<>();
        for (int linesPassed = 0; lines.size() > linesPassed; linesPassed += 5){
            var owner = getOwner(lines.get(linesPassed));
            var creditCards = getCreditCardNumbers(lines.get(linesPassed + 1));
            var balance =  getBalance(lines.get(linesPassed + 2));
            var username = getUsername(lines.get(linesPassed + 3));
            var password = getPassword(lines.get(linesPassed + 4));
            Account account = new Account(owner, creditCards, balance, username, password);
            accountList.add(account);
        }
        return accountList;
    }


    //find creditCard By Number method !
    public List<CreditCard> findCreditCardsByNumber(String line){
        List<CreditCard> creditCards = new ArrayList<>();
        var numbers = line.split(", ");
        for(var number : numbers){
            creditCards.add(mapNumberToCreditCard.get(number));
        } return creditCards;
    }


    //              Get method
    //                  For serializer
    protected String getCreditCardNumbers(List<String> creditCardsNumbers) {
        StringBuilder finalLine = new StringBuilder();
        for (var number : creditCardsNumbers){
            finalLine.append(number).append(", ");
        }
        finalLine.deleteCharAt(finalLine.lastIndexOf(", "));
        return finalLine.toString();
    }


    //              Get methods
    //                  For parser
    private List<String> getCreditCardNumbers(String line) {

        var attributes = line.replaceAll(",", "").split(" ");
        List<String> creditCardList = new ArrayList<>();
        for (int i = 1; i < attributes.length ; i++) {
            creditCardList.add(attributes[i]);
        }
        return creditCardList;
    }

    private List<String> getAllLines() {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPassword(String line) {
        var attributes = line.split(" ");
        return attributes[1];
    }

    private String getUsername(String line) {
        var attributes = line.split(" ");
        return attributes[1];
    }

    private Double getBalance(String line) {
        var attributes = line.split(" ");
        return Double.parseDouble(attributes[1]);
    }

    private String getOwner(String line) {
        var attributes = line.split(" ");
        var firstName = attributes[1];
        var lastName = attributes[2];
        var fullName = firstName + " " + lastName;
        return fullName;
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
