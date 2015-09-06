package main.domain.account;

public interface Encryptor {
    EncryptedPassword encrypt(Password password);
    boolean matches(Password password, EncryptedPassword encryptedPassword);
}
