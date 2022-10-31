package org.atm.module;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public record CreditCard(String number, String pin,
                         double balance, Boolean active) {
    public CreditCard{
        Objects.requireNonNull(number, "Number can't be null");
        if (pin.length() != 4){
            throw new IllegalArgumentException("Pin must be 4 digits");
        }
        if (balance < 0){
            throw new IllegalArgumentException("Balance can't be negative");
        }
    }

    public static CreditCard createNewCreditCard(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter card number: ");
        String number = sc.next();
        System.out.println("Enter card pin: ");
        String pin = sc.next();
        System.out.println("Enter card balance: ");
        Double balance = Double.parseDouble(sc.next());
        System.out.println("True / False if card is active: ");
        Boolean active = Boolean.parseBoolean(sc.next().toUpperCase(Locale.ROOT));
        return new CreditCard(number,pin,balance,active);
    }



}
