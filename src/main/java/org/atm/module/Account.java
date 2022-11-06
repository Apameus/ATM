package org.atm.module;

import java.util.List;

public record Account(String owner, List<String> creditCardsNumbers, Double balance, String username, String password) {



}
