package main.security;

import main.domain.account.EncryptedPassword;
import main.domain.account.Encryptor;
import main.domain.account.Password;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class JasyptEncryptor implements Encryptor {
    private final StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

    public EncryptedPassword encrypt(Password password) {
        String salt = makeSalt();
        return new EncryptedPassword(salt, encryptor.encryptPassword(salt + password));
    }

    private String makeSalt() {
        try {
            byte[] saltBytes = new byte[32];
            SecureRandom.getInstanceStrong().nextBytes(saltBytes);
            return new String(saltBytes);
        } catch (NoSuchAlgorithmException ignored) {
            return null;
        }
    }

    public boolean matches(Password password, EncryptedPassword encryptedPassword) {
        return encryptor.checkPassword(encryptedPassword.getSalt() + password, encryptedPassword.getHash());
    }
}
