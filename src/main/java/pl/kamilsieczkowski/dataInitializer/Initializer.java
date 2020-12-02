package pl.kamilsieczkowski.dataInitializer;

import pl.kamilsieczkowski.utils.User;

import java.util.*;

import static pl.kamilsieczkowski.constants.Privilege.*;

public class Initializer {
    public List<User> initializeUsersList() {
        List<User> users = new ArrayList<>();
        users.add(new User("Admin", "pass", ADMIN));
        users.add(new User("Employee", "pass", EMPLOYEE));
        users.add(new User("Client", "Client", CLIENT));
        return users;
    }
}
