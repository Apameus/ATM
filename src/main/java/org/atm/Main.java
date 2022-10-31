package org.atm;
import org.atm.module.CreditCard;

import java.util.Scanner;



/*
___ Insert CreditCard -Yes, -No. Pin -Success, Failure. Option menu.

-ATM
Balance of ATM
Authentication
OptionList



*/


public class Main
{
    public static void main(String[] args)
    {
        CreditCard creditCard = CreditCard.createNewCreditCard();
        int atm_balance = 1_000_000;

        double creditCard_balance = creditCard.balance();
        int withdraw, deposit;

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while(choice != 4) {
            choice = getChoice(sc);
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter money to be withdrawn: ");

                    withdraw = sc.nextInt();

                    if (withdraw <= 0){
                        System.err.println("Invalid number");
                    }

                    if (creditCard_balance >= withdraw) {
                        creditCard_balance = creditCard_balance - withdraw;
                        System.out.println("Please collect your money");
                    } else {
                        System.err.println("Insufficient Balance");
                    }
                    System.out.println("");
                }
                case 2 -> {
                    System.out.print("Enter money to be deposited: ");

                    deposit = sc.nextInt();

                    //check if the amount is less or equal to zero
                    if (deposit <= 0) {
                        System.err.println("The value must be greater than 0");
                    }

                    //add the deposit amount to the total balance
                    creditCard_balance = creditCard_balance + deposit;
                    System.out.println("Your Money has been successfully deposited");
                    System.out.println("");
                }
                case 3 -> {
                    System.out.println("Balance : " + creditCard_balance);
                    System.out.println("");
                }
            }
        }
    }

    private static int getChoice(Scanner sc) {
        System.out.println("ATM OF DOOM !");
        System.out.println("Choose 1 for Withdraw");
        System.out.println("Choose 2 for Deposit");
        System.out.println("Choose 3 for Check Balance");
        System.out.println("Choose 4 for EXIT");
        System.out.print("Choose the operation you want to perform: ");

        //get choice from user
        return sc.nextInt();
    }


}