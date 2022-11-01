package org.atm.serialization;

import org.atm.module.CreditCard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

    /*
    public List<CreditCard> parseCreditCards(){
        List<String> lines = getAllLines();
        List<CreditCard> creditCardList = new ArrayList<>();
        for (var line : lines){
            String[] attributes = line.split("\n");
            var number = attributes[0];
            var pin = getPin(attributes);
            var balance = attributes[2];
            var active = attributes[3];
            //var fullName ;
        }
    }
     */

    private static String getPin(String[] attributes) {
        return attributes[1];
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
