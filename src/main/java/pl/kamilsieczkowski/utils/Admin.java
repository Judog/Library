package pl.kamilsieczkowski.utils;

public class Admin extends Employee {
    private final String PRIVILIGE;

    public Admin(String login, String password) {
        super(login, password);
        PRIVILIGE = "ADMIN";
    }
}
