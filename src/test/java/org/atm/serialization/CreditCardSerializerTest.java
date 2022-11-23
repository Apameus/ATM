package org.atm.serialization;

import org.atm.module.CreditCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CreditCardSerializerTest extends CreditCardSerializer{



        @Test
        @DisplayName("SerializeCreditCard")
        void serializeCreditCard(){
            CreditCard creditCard = new CreditCard("1234567890123456", "2020",
                                                    1000,false,"Ioannis Tzortzinis");
            String expectedLine = ("""
                    CreditCard_Number: %s
                    Pin: %s
                    Balance: %s
                    Active: %s
                    Owner: %s
                    """).formatted(creditCard.number(), creditCard.pin(),
                                                    creditCard.balance(), creditCard.active(), creditCard.owner());
    //   Need refactor !
    //        assertEquals(expectedLine,saveCreditCards(List.of(creditCard)).get(0));

        }

        @Test
        @DisplayName("ParseCreditCard")
        void parseCreditCard(){
            List<CreditCard> creditCardList = parseCreditCards();
            CreditCard creditCard1 = creditCardList.get(0);

            CreditCard expectedCreditCard1 = new CreditCard("1234_5678_9012_3456", "2020", 1000, false, "Ioannis Tzortzinis");
            assertEquals(creditCard1,expectedCreditCard1);
        }

        @Test
        @DisplayName("ParseTwoCreditCards")
        void parseTwoCreditCards(){
            List<CreditCard> creditCardList = parseCreditCards();
            CreditCard creditCard1 = creditCardList.get(0);
            CreditCard creditCard2 = creditCardList.get(1);

            CreditCard expectedCreditCard1 = new CreditCard("1234_5678_9012_3456", "2020", 1000, false, "Ioannis Tzortzinis");
            CreditCard expectedCreditCard2 = new CreditCard("9027_4201_3654_1871", "1423", 30000, true, "Ilias Tzortzinis");


            assertEquals(creditCard1,expectedCreditCard1);
            assertEquals(creditCard2, expectedCreditCard2);
        }

        @Test
        @DisplayName("ParseZeroCreditCards")
        void parseZeroCreditCards(){
            setPah("EmptyFileTest.txt");
            List<CreditCard> creditCardList = parseCreditCards();

        }
}