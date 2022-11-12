package org.atm.repositories;

import org.atm.module.Account;
import org.atm.serialization.CreditCardSerializer;

import java.util.Scanner;

public class AccountRepository {

    public Account changePassword(Account account){
        System.out.println("Old password: " + account.password());
        String newPassword = getInputString("New password: ");
        Account returnedAccount = new Account(account.owner(), account.creditCardsNumbers(), account.balance(), account.username(), newPassword);
        return returnedAccount;
    }

    public Account changeUsername(Account account){
        System.out.println("Old username: " + account.username());
        String newUsername = getInputString("New username: ");
        Account returnedAccount = new Account(account.owner(), account.creditCardsNumbers(), account.balance(), newUsername, account.password());
        return returnedAccount;
    }

    public void viewInfo(Account account){
        System.out.println(String.format("""
                Owner: '%s'
                CreditCards: '%s'
                Balance: '%s'
                Username: '%s'
                Password: '%s'
                """).formatted(account.owner(), CreditCardSerializer.findCreditCardsByNumber(account.creditCardsNumbers())));
    }

    public void viewBalance(Account account){
        System.out.println(account.balance());
    }



    private String getInputString(String msg) {
        System.out.println(msg);
        return String.valueOf(new Scanner(System.in));

    }
    private Double getInput(String msg) {
        System.out.println(msg);
        return Double.parseDouble(String.valueOf(new Scanner(System.in)));

    }
}
