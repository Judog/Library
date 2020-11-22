package pl.kamilsieczkowski.utils;

public class User {
    private String login;
    private String password;
    private final Enum privilege;

    public User(final String login, final String password, final Enum privilege) {
        this.login = login;
        this.password = password;
        this.privilege = privilege;
    }
}
