package main.account;

public class User {
    private String id;
    private Email email;
    private Password password;

    public User() {
        id = "";
        email = new Email("");
        password = new Password("");
    }

    private User(String id, Email email, Password password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public boolean hasId() {
        return !id.isEmpty();
    }

    public String getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id.trim();
    }

    public void setEmail(Email email) {
        this.email = email == null ? new Email("") : email;
    }

    public void setPassword(Password password) {
        this.password = password == null ? new Password("") : password;
    }

    public User copy() {
        return new User(id, email, password);
    }
}
