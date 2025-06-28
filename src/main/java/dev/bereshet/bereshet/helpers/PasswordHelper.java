package dev.bereshet.bereshet.helpers;

public class PasswordHelper {
    public static boolean isPassvordValid(String password) {
        return password.length() >= 8;
    }
}
