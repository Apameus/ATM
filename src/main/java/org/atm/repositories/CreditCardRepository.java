package org.atm.repositories;

import org.atm.module.CreditCard;

import java.util.List;
import java.util.Scanner;

public class CreditCardRepository {

//    private final List<CreditCard> creditCards;
//
//    public CreditCardRepository(){
//
//        this.creditCards = creditCards;
//    }



    public CreditCard deposit(CreditCard creditCard){
        Double depositAmount = getInput("Deposit amount: ");
        Double newBalance = depositAmount + creditCard.balance();
        CreditCard returnedCreditCard = new CreditCard(creditCard.number(), creditCard.pin(), newBalance, creditCard.active(), creditCard. owner());
        return returnedCreditCard;
    }

    public CreditCard withdraw(CreditCard creditCard){
        Double withdrawAmount = getInput("Withdraw amount: ");
        Double newBalance = withdrawAmount - creditCard.balance();
        CreditCard returnedCreditCard = new CreditCard(creditCard.number(), creditCard.pin(), newBalance, creditCard.active(), creditCard. owner());
        return returnedCreditCard;
    }

    public void viewBalance(CreditCard creditCard){
        System.out.println(creditCard.balance());
    }

    private Double getInput(String msg) {
        System.out.println(msg);
        return Double.parseDouble(String.valueOf(new Scanner(System.in)));

    }

}
