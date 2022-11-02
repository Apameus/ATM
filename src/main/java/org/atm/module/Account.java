package org.atm.module;

import java.util.List;

public record Account(String owner, List<CreditCard> creditCards, Double balance, String username, String password) {

    ;
    ;
    ;

    ;

    public Account(String owner, List<CreditCard> creditCards,Double balance ,String username, String password){

        this.owner = owner;
        this.creditCards = creditCards;
        this.balance = balance;
        this.username = username;
        this.password = password;

        if (!owner.contains(" ")){
//            owner =
        }
    }
}
