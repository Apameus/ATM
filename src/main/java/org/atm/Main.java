package org.atm;
import org.atm.module.Account;
import org.atm.module.CreditCard;
import org.atm.repositories.AccountRepository;
import org.atm.repositories.CreditCardRepository;
import org.atm.serialization.AccountSerializer;
import org.atm.serialization.CreditCardSerializer;

import java.util.List;
import java.util.Scanner;




public class Main  {

    //Repositories
    static AccountRepository accountRepository = new AccountRepository();
    static CreditCardRepository creditCardRepository = new CreditCardRepository();

    //Serializers
    static AccountSerializer accountSerializer = new AccountSerializer();
    static CreditCardSerializer creditCardSerializer = new CreditCardSerializer();

    //List CreditCard's
    static List<CreditCard> creditCardList = creditCardSerializer.parseCreditCards();
    //List Account's
    static List<Account> accountList = accountSerializer.parseAccounts();

    public static void main(String[] args) {


        String option = getInput("CreditCard, Account, Exit: ");
        while (!option.equals("exit")){
            login(option, creditCardList, accountList);
            option = getInput("CreditCard, Account, Exit: ");

        }

    }

    private static void login(String option, List<CreditCard> creditCardList, List<Account> accountList) {
        if (option.equalsIgnoreCase("creditCard")){
            var number = getInput("Number: ");
            var pin = getInput("Pin: ");
            for (var creditCard : creditCardList){
                if (creditCard.number().equals(number) & creditCard.pin().equals(pin)){
                    System.out.println("successful login");
                    creditCardCode(creditCard);
                }
            }
        }
        else if (option.equalsIgnoreCase("account")){
            if (option.equalsIgnoreCase("account")){
                var username = getInput("Username: ");
                var password = getInput("Password: ");
                for (var account : accountList){
                    if (account.username().equalsIgnoreCase(username) & account.password().equalsIgnoreCase(password)){
                        System.out.println("successful login");
                        accountCode(account);
        }
                }}}
    }

    private static void accountCode(Account account) {
        accountOptionMenu(account);
    }

    private static void accountOptionMenu(Account account) {
        String option = getInput("View_info, View_balance, Change_info, Exit: ");
        while (!option.equalsIgnoreCase("exit")) {
            switch (option.toLowerCase()) {
                case "view_info" -> {
                    accountRepository.viewInfo(account);
                }
                case "view_balance" -> {
                    accountRepository.viewBalance(account);
                }
                case "change_info" -> {
                    account = accountRepository.changeUsername(account);
                    account = accountRepository.changePassword(account);
                }
                default -> {
                }
            }
            option = getInput("View_info, View_balance, Change_info, Exit: ");
        }
    }

    private static void creditCardCode(CreditCard creditCard) {
        creditCardOptionMenu(creditCard);
    }

    private static void creditCardOptionMenu(CreditCard creditCard) {
        String option = getInput("Withdraw, Deposit, View_balance, Exit: ");
        while (!option.equalsIgnoreCase("exit")) {
            switch (option.toLowerCase()) {
                case "withdraw" -> {
                    creditCardRepository.withdraw(creditCard);
                }
                case "deposit" -> {
                    creditCardRepository.deposit(creditCard);
                }
                case "view_balance" -> {
                    creditCardRepository.viewBalance(creditCard);
                }
                default -> {
                }
            }
        }
    }

    public static String getInput(String msg){
        System.out.print(msg);
        return String.valueOf(new Scanner(System.in).next());
    }
}