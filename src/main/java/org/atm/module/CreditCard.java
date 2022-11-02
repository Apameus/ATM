package org.atm.module;

import org.atm.services.Console;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public record CreditCard(String number, String pin,
                         double balance, Boolean active, String owner) implements Console {

    static Scanner sc = new Scanner(System.in);

    public CreditCard{
        Objects.requireNonNull(number, "Number can't be null");
        if (pin.length() != 4){
            throw new IllegalArgumentException("Pin must be 4 digits");
        }
        if (balance < 0){
            throw new IllegalArgumentException("Balance can't be negative");
        }
    }

    public CreditCard createNewCreditCard(){

        String number = getInput("Enter card number: ");
        String pin = getInput("Enter card pin: ");
        Double balance = Double.parseDouble(getInput("Enter card balance: "));
        Boolean active = Boolean.parseBoolean(getInput("Enter card ").toLowerCase(Locale.ROOT));
        String owner = getInput("Owner: ");
        return new CreditCard(number,pin,balance,active,owner);
    }


    @Override
    public String getInput(String message) {
        System.out.println(message);
        return sc.next();
    }
}
