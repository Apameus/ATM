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
        //check if depositAmount <= 0
        while (depositAmount <= 0){
            System.err.println("The amount must be greater than 0");
            System.out.println();
            depositAmount = getInput("Deposit amount: ");
        }
        Double newBalance = depositAmount + creditCard.balance();
        CreditCard returnedCreditCard = new CreditCard(creditCard.number(), creditCard.pin(), newBalance, creditCard.active(), creditCard. owner());
        return returnedCreditCard;
    }

    public CreditCard withdraw(CreditCard creditCard){
        //check if balance <= 0
        if (creditCard.balance() <= 0){
            System.err.println("You have no money to withdraw");
            System.out.println();
            return creditCard;
        }
        //get the input
        Double withdrawAmount = getInput("Withdraw amount: ");
        //check if balance - withdrawn < 0
        if (creditCard.balance() - withdrawAmount < 0){
            System.err.println("You don't have enough money !");
            System.out.println();
            return creditCard;
        }
        //check withdraw > 0
        while (withdrawAmount <= 0){
            System.err.println("The amount must be greater than 0");
            System.out.println();
            withdrawAmount = getInput("Withdrawn amount: ");
        }
        Double newBalance = creditCard.balance() - withdrawAmount;
        CreditCard returnedCreditCard = new CreditCard(creditCard.number(), creditCard.pin(), newBalance, creditCard.active(), creditCard. owner());
        return returnedCreditCard;
    }

    public void viewBalance(CreditCard creditCard){
        System.out.println(creditCard.balance());
    }

    private Double getInput(String msg) {
        System.out.println(msg);
        return new Scanner(System.in).nextDouble();

    }

}
