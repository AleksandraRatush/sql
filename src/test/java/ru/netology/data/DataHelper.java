package ru.netology.data;

import lombok.Value;

public class DataHelper {

    public static User getUser() {
        return new User ("vasya", "qwerty123");
    }

    public static User getUserWithFakePassword() {
        return new User ("vasya", "qwerty1234");
    }

    public static User getUserWithFakeLogin() {
        return new User ("vasya1", "qwerty123");
    }

    public static String makeCodeFake(String code) {
        return code.replace(code.toCharArray()[0], (char) (code.toCharArray()[0] + 1));
    }

    @Value
    public static class User {
        String login;
        String password;
    }

}
