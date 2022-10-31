package org.atm.serialization;

import org.atm.module.CreditCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    class CreditCardSerializerTest extends CreditCardSerializer{



        @Test
        @DisplayName("SerializeCreditCard")
        void serializeCreditCard(){
            CreditCard creditCard = new CreditCard("1234567890123456", "2020",
                                                    1000,false);
            String expectedLine = "%s\nPIN: %s\n%s$\n%s\n".formatted(creditCard.number(), creditCard.pin(),
                                                    creditCard.balance(), creditCard.active());

            assertEquals(expectedLine,saveCreditCards(List.of(creditCard)).get(0));

        }

}