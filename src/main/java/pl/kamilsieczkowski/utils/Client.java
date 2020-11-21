package pl.kamilsieczkowski.utils;

import static pl.kamilsieczkowski.constants.Constants.CLIENT;

public class Client {
    protected String login;
    protected String password;
    private final String PRIVILIGE;

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
        this.PRIVILIGE = CLIENT;
    }
}
