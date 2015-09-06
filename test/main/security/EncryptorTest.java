package main.security;

import main.domain.account.EncryptedPassword;
import main.domain.account.Encryptor;
import main.domain.account.Password;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public abstract class EncryptorTest {
    Encryptor encryptor;

    @Before
    public void setUp() throws Exception {
        encryptor = makeEncryptor();
    }

    protected abstract Encryptor makeEncryptor();

    @Test
    public void givenTheSamePasswordAsBeforeEncryption_itShouldMatch() {
        Password plainTextPassword = new Password("some user-entered string");
        EncryptedPassword encryptedPassword = encryptor.encrypt(plainTextPassword);
        assertTrue(encryptor.matches(plainTextPassword, encryptedPassword));
    }

    @Test
    public void givenTheDifferentPasswordAfterEncryption_itShouldNotMatch() {
        EncryptedPassword encryptedPassword = encryptor.encrypt(new Password("some user-entered string"));
        assertFalse(encryptor.matches(new Password("some different user-entered string"), encryptedPassword));
    }
}
