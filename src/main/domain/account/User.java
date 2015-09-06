package main.domain.account;

import main.domain.Entity;

public class User extends Entity {
    private Email email;
    private EncryptedPassword password;

    public User() {
        this("", Email.EMPTY, EncryptedPassword.EMPTY);
    }

    private User(String id, Email email, EncryptedPassword password) {
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

    public EncryptedPassword getPassword() {
        return password;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPassword(EncryptedPassword password) {
        this.password = password;
    }
}
