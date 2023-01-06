package org.atm;

import org.atm.module.Account;
import org.atm.module.CreditCard;
import org.atm.repositories.AccountRepository;
import org.atm.repositories.CreditCardRepository;
import org.atm.serialization.AccountSerializer;
import org.atm.serialization.CreditCardSerializer;

import java.util.List;
import java.util.Scanner;

public class Manager {
    //Repositories
    CreditCardRepository creditCardRepository = new CreditCardRepository();
    AccountRepository accountRepository = new AccountRepository();

    //Serializers
    CreditCardSerializer creditCardSerializer = new CreditCardSerializer();
    AccountSerializer accountSerializer = new AccountSerializer();

    //List CreditCard's
    List<CreditCard> creditCardList = creditCardSerializer.parseCreditCards();
    //List Account's
    List<Account> accountList = accountSerializer.parseAccounts();

    /**
     * <b>Contractor</b>
     */
    public Manager(){
        start();
    }

    /**
     * <b>Executing the code</b>
     */
    private void start() {
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
            login(option);
            option = getInput("CreditCard, Account, Exit: ");

        }
    }

    /**
     * <b>Checks</b> if the input info is equal with the info of the analogous list and <b>run the option menu methods</b>,
     * else error message and re-get the option.
     * @param option card || account.
     */
    private void login(String option) {
        if (option.equalsIgnoreCase("creditCard")){
            var number = getInput("Number: ");
            var pin = getInput("Pin: ");
            //for every card in the list
            for (var creditCard : creditCardList){
                //login authentication
                if (creditCard.number().equals(number) & creditCard.pin().equals(pin)){
                    System.out.println("Successful login!");
                    System.out.println();
                    creditCardOptionMenu(creditCard);
                    return;}
            }
            //Wrong answer
            System.err.println("Wrong username or password!");
            System.out.println();
        }
        else if (option.equalsIgnoreCase("account")){
            var username = getInput("Username: ");
            var password = getInput("Password: ");
            //for every account
            for (var account : accountList){
                //login authentication
                if (account.username().equals(username) & account.password().equals(password)){
                    System.out.println("Successful login !");
                    System.out.println();
                    accountOptionMenu(account, accountList, accountRepository, accountSerializer);
                    return;}
            }
            //Wrong answer
            System.err.println("Wrong username or password!");
            System.out.println();
        }
    }

    private static void accountOptionMenu(Account account, List<Account> accountList, AccountRepository accountRepository, AccountSerializer accountSerializer) {
        String option = getInput("View_info, View_balance, Change_info, Exit: ");
        while (!option.equalsIgnoreCase("View_info") & !option.equalsIgnoreCase("View_balance") & !option.equalsIgnoreCase("Change_info") & !option.equalsIgnoreCase("Exit")){
            System.err.println("Your answer is not accurate!");
            System.out.println();
            option = getInput("View_info, View_balance, Change_info, Exit: ");
        }
        while (!option.equalsIgnoreCase("exit")) {
            switch (option.toLowerCase()) {

                case "view_info" -> accountRepository.viewInfo(account);

                case "view_balance" -> accountRepository.viewBalance(account);

                case "change_info" -> {
                    //New acc with the changed info
                    Account updatedAccount = accountRepository.changeUsername(account);
                    updatedAccount = accountRepository.changePassword(updatedAccount);
                    //update the old one
                    account = updatedAccount;

                    //find the position of the current acc in the list
                    var x = 0;
                    for (var e : accountList){
                        if (e.owner().equals(account.owner())){
                            break;}
                        x++;
                    }
                    //remove the previous acc from the list
                    accountList.remove(x);
                    //add the new one
                    accountList.add(x, updatedAccount);
                    //serialize the hole list
                    accountSerializer.saveAccounts(accountList);
                }
                default -> {}
            }
            option = getInput("View_info, View_balance, Change_info, Exit: ");
        }
    }

    private void creditCardOptionMenu(CreditCard creditCard) {
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
                    refresh(creditCard);}

                case "deposit" -> {
                    creditCard = creditCardRepository.deposit(creditCard);
                    refresh(creditCard);}
                case "view_balance" -> creditCardRepository.viewBalance(creditCard);

                case "exit" -> {continue;}

                default -> System.err.println("Wrong answer!");
            }

            option = getInput("Withdraw, Deposit, View_balance, Exit: ");
        }
    }

    /**
     * <b>Update's</b> the Credit_card & Account lists according to our changes <b>and save's</b> them to the files.
     *
     * @param creditCard
     */
    private void refresh(CreditCard creditCard) {
        //find the creditCard position in creditCardList
        var x = 0;
        for (var e : creditCardList){
            if (e.number().equals(creditCard.number())){
                break;
            }
            x++;
        }
        //remove the previous creditCard
        creditCardList.remove(x);
        //add the new one
        creditCardList.add(x, creditCard);
        //serialize it
        creditCardSerializer.saveCreditCards(creditCardList);

        //refresh the account list
        accountList = accountSerializer.parseAccounts();
        //save it to file
        accountSerializer.saveAccounts(accountList);
    }

    public static String getInput(String msg){
        System.out.print(msg);
        return String.valueOf(new Scanner(System.in).next());
    }
}
