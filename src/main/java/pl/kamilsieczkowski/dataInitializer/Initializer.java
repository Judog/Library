package pl.kamilsieczkowski.dataInitializer;

import pl.kamilsieczkowski.utils.Admin;
import pl.kamilsieczkowski.utils.Client;
import pl.kamilsieczkowski.utils.Employee;

import java.util.*;

public class Initializer {
    public List<Client> initializeClientList() {
        ArrayList<Client> admins = new ArrayList<>();
        admins.add(new Admin("Admin", "pass"));
        admins.add(new Employee("Employee", "pass"));
        admins.add(new Client("Client", "Client"));
        return admins;
    }
}
