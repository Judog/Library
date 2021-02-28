package pl.kamilsieczkowski.dto;

import pl.kamilsieczkowski.model.User;

public class UserDTO {
    private static User user;
    private static boolean isUserLogged;

    public static User getUser() {
        return user;
    }

    public static boolean isUserLogged() {
        return isUserLogged;
    }

    public static void setUser(User user) {
        isUserLogged = true;
        UserDTO.user = user;
    }
}
