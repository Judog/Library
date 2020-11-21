package pl.kamilsieczkowski.utils;

import static pl.kamilsieczkowski.constants.Constants.ADMIN;

public class Admin extends Employee {
    private final String PRIVILIGE;

    public Admin(String login, String password) {
        super(login, password);
        PRIVILIGE = ADMIN;
    }
}
