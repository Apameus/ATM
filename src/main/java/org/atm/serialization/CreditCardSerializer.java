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
                        + creditCard.balance() + "$" + "\n" + creditCard.active() + "\n";
            lines.add(line);
        }
        return lines;
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
