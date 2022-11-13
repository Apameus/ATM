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
        System.out.println("""
                Owner: '%s'
                CreditCards: '%s'
                Balance: '%f'
                Username: '%s'
                Password: '%s'
                """.formatted(account.owner(), CreditCardSerializer.findCreditCardsByNumber(account.creditCardsNumbers()),
                            account.balance(), account.username(), account.password()));
    }

    public void viewBalance(Account account){
        System.out.println(account.balance());
    }



    private String getInputString(String msg) {
        System.out.print(msg);
        return String.valueOf(new Scanner(System.in).next());

    }
    private Double getInput(String msg) {
        System.out.print(msg);
        return Double.parseDouble(String.valueOf(new Scanner(System.in).next()));

    }
}
