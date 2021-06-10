package client.controllers;

public class User {

    private Long id;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String adress;

    public User(String username, String name, String lastname, String email, String adress, Long id) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.adress = adress;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
