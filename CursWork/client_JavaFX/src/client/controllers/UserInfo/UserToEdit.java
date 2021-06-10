package client.controllers.UserInfo;

public class UserToEdit {

    static String username;
    static Long id;
    static String type;
    static String email;
    static String adress;
    static String name;
    static String lastname;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserToEdit.username = username;
    }

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        UserToEdit.id = id;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        UserToEdit.type = type;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserToEdit.email = email;
    }

    public static String getAdress() {
        return adress;
    }

    public static void setAdress(String adress) {
        UserToEdit.adress = adress;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserToEdit.name = name;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        UserToEdit.lastname = lastname;
    }
}
