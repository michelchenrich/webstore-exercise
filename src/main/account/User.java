package main.account;

public class User {
    private String id;
    private String email;
    private String password;

    public User() {
        id = "";
        email = "";
        password = "";
    }

    private User(String id, String email, String password) {
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id.trim();
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email;
    }

    public void setPassword(String password) {
        this.password = password == null ? "" : password;
    }

    public User copy() {
        return new User(id, email, password);
    }
}
