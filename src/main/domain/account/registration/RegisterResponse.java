package main.domain.account.registration;

public class RegisterResponse {
    public boolean success;
    public String id;
    public boolean invalidEmail;
    public boolean invalidPassword;
    public boolean invalidPasswordConfirmation;
}
