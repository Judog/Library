package pl.kamilsieczkowski.utils;

public class Employee extends Client{
    private final String PRIVILIGE;
    public Employee(String login, String password) {
        super(login, password);
        PRIVILIGE = "EMPLOYEE";
    }
}
