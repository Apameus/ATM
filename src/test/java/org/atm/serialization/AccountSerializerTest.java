package org.atm.serialization;

import org.atm.module.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountSerializerTest extends AccountSerializer{

        @Test
        @DisplayName("SerializeAccount")
        void serializeAccount(){
            Account account = new Account("Ioannis Tzortzinis",
                    List.of("1234_5678_9012_3456, 4342_5801_7766_3211"),
                    10.0,
                    "Apameus",
                    "Apameus123");
            String expectedLine = ("Owner: %s\nCC: %s\nBalance: %s\nUsername: %s\nPassword: %s").formatted(account.owner(), account.creditCardsNumbers(),
                                    account.balance(), account.username(), account.password());

            assertEquals(expectedLine, saveAccounts(List.of(account)).get(0));
        }

    @Test
    @DisplayName("SerializeTwoAccounts")
    void serializeTwoAccounts(){
        Account account1 = new Account("Ioannis Tzortzinis",
                List.of("1234_5678_9012_3456, 4342_5801_7766_3211"),
                10.0,
                "Apameus",
                "Apameus123");

        Account account2 = new Account("Ilias Tzortzinis",
                List.of("1234_5678_9012_3456, 4342_5801_7766_3211"),
                100.0,
                "DoomArchitect",
                "DoomArchitect123");

        String expectedLine1 = ("Owner: %s\nCC: %s\nBalance: %s\nUsername: %s\nPassword: %s").formatted(account1.owner(), account1.creditCardsNumbers(),
                account1.balance(), account1.username(), account1.password());

        String expectedLine2 = ("Owner: %s\nCC: %s\nBalance: %s\nUsername: %s\nPassword: %s").formatted(account2.owner(), account2.creditCardsNumbers(),
                account2.balance(), account2.username(), account2.password());

        String expectedParagraph = expectedLine1 + " " +  expectedLine2;

        assertEquals(expectedLine1, saveAccounts(List.of(account1)).get(0));

        assertEquals(expectedLine2, saveAccounts(List.of(account2)).get(0));

        assertEquals(expectedParagraph, saveAccounts(List.of(account1, account2)));
    }

}