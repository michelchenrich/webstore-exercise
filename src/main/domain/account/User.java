package main.domain.account;

import main.domain.Entity;

public class User extends Entity {
    private Email email;
    private Password password;

    public User() {
        this("", new Email(""), new Password(""));
    }

    private User(String id, Email email, Password password) {
        super(id);
        this.email = email;
        this.password = password;
    }

    public User copy() {
        return new User(id, email, password);
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public void setEmail(Email email) {
        this.email = email == null ? new Email("") : email;
    }

    public void setPassword(Password password) {
        this.password = password == null ? new Password("") : password;
    }

    public boolean matchesPassword(Password password) {
        return this.password.equals(password);
    }
}
