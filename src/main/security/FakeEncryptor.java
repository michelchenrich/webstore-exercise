package main.security;

import main.domain.account.EncryptedPassword;
import main.domain.account.Encryptor;
import main.domain.account.Password;

public class FakeEncryptor implements Encryptor {
    public EncryptedPassword encrypt(Password password) {
        return new EncryptedPassword("salt", "hashed " + password);
    }

    public boolean matches(Password password, EncryptedPassword encryptedPassword) {
        return encrypt(password).equals(encryptedPassword);
    }
}
