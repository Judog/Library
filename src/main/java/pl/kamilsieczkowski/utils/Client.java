package pl.kamilsieczkowski.utils;

public class Client {
    protected String login;
    protected String password;
    private final String PRIVILIGE;

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
        this.PRIVILIGE = "CLIENT";
    }
}
