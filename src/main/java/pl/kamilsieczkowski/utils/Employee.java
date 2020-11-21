package pl.kamilsieczkowski.utils;

import static pl.kamilsieczkowski.constants.Constants.EMPLOYEE;

public class Employee extends Client{
    private final String PRIVILIGE;
    public Employee(String login, String password) {
        super(login, password);
        PRIVILIGE = EMPLOYEE;
    }
}
