package pl.kamilsieczkowski.model;

import java.util.Objects;

public class User {
    private final String login;
    private final String password;
    private final String privilege;

    public String getLogin() {
        return login;
    }

    public User(final String login, final String password, final String privilege) {
        this.login = login;
        this.password = password;
        this.privilege = privilege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}