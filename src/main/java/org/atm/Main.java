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

        //get the input
        String option = getInput("CreditCard, Account, Exit: ");
        //check the answer
        while (!option.equalsIgnoreCase("creditCard") & !option.equalsIgnoreCase("Account") & !option.equalsIgnoreCase("Exit")){
            System.err.println("Your answer is not accurate!");
            System.out.println();
            option = getInput("CreditCard, Account, Exit: ");
        }
        //continue
        while (!option.equalsIgnoreCase("exit")){
            login(option, creditCardList, accountList);
            option = getInput("CreditCard, Account, Exit: ");

        }

    }
    //login code
    private static void login(String option, List<CreditCard> creditCardList, List<Account> accountList) {
        if (option.equalsIgnoreCase("creditCard")){
            var number = getInput("Number: ");
            var pin = getInput("Pin: ");
            //for every card in the list
            for (var creditCard : creditCardList){
                //login authentication
                if (creditCard.number().equals(number) & creditCard.pin().equals(pin)){
                    System.out.println("Successful login!");
                    System.out.println();
                    creditCardCode(creditCard);}
                //Wrong answer
                else {
                    System.err.println("Wrong number or pin!");
                    System.out.println();
                }
            }
        }
        else if (option.equalsIgnoreCase("account")){
                var username = getInput("Username: ");
                var password = getInput("Password: ");
                //for every account
                for (var account : accountList){
                    //login authentication
                    if (account.username().equalsIgnoreCase(username) & account.password().equalsIgnoreCase(password)){
                        System.out.println("Successful login !");
                        System.out.println();
                        accountCode(account);}
                    //Wrong answer
                    else {
                        System.err.println("Wrong username or password!");
                        System.out.println();
                    }
                }}

    }

    private static void accountCode(Account account) {
        accountOptionMenu(account);
    }

    private static void accountOptionMenu(Account account) {
        String option = getInput("View_info, View_balance, Change_info, Exit: ");
        while (!option.equalsIgnoreCase("View_info") & !option.equalsIgnoreCase("View_balance") & !option.equalsIgnoreCase("Change_info") & !option.equalsIgnoreCase("Exit")){
            System.err.println("Your answer is not accurate!");
            System.out.println();
            option = getInput("View_info, View_balance, Change_info, Exit: ");
        }
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
        //check the answer
        while (!option.equalsIgnoreCase("Withdraw") & !option.equalsIgnoreCase("Deposit") & !option.equalsIgnoreCase("View_balance") & !option.equalsIgnoreCase("Exit")){
            System.err.println("Your answer is not accurate!");
            System.out.println();
            option = getInput("Withdraw, Deposit, View_balance, Exit: ");
        }
        //while answer != exit..
        while (!option.equalsIgnoreCase("exit")) {
            switch (option.toLowerCase()) {
                case "withdraw" -> {
                    creditCard = creditCardRepository.withdraw(creditCard);
                }
                case "deposit" -> {
                    creditCard = creditCardRepository.deposit(creditCard);
                }
                case "view_balance" -> {
                    creditCardRepository.viewBalance(creditCard);
                }
                default -> {
                }
            }
            option = getInput("Withdraw, Deposit, View_balance, Exit: ");
        }
    }

    public static String getInput(String msg){
        System.out.print(msg);
        return String.valueOf(new Scanner(System.in).next());
    }
}