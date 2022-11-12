package org.atm.services;

import java.util.List;

public interface Console <T>{

    T getInput(String message);

    String optionMenu(List<String> options);

}
