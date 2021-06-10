package client.controllers.UserInfo;

public class User {

    static String username;
    static String password;
    static Long id;
    static String type;
    static String email;
    static String adress;
    static String name;
    static String lastname;

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getAdress() {
        return adress;
    }

    public static void setAdress(String adress) {
        User.adress = adress;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        User.lastname = lastname;
    }

    public User() {
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        User.id = id;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        User.type = type;
    }
}
