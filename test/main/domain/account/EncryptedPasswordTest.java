package main.domain.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class EncryptedPasswordTest {
    private EncryptedPassword makeEncrypted(String salt, String hash) {
        return new EncryptedPassword(salt, hash);
    }

    @Test
    public void itKeepsTheValuesSet() {
        EncryptedPassword password = makeEncrypted("55e8fd90d8699a04b5f41b8e1", "55e8fd90d8699a04b5f41b8e2");
        assertEquals("55e8fd90d8699a04b5f41b8e1", password.getSalt());
        assertEquals("55e8fd90d8699a04b5f41b8e2", password.getHash());
    }

    @Test
    public void equalValues_meansEqualsPasswords() {
        EncryptedPassword correctPassword = makeEncrypted("55e8fd90d8699a04b5f41b8e", "55e8fd90d8699a04b5f41b8e");
        EncryptedPassword incorrectPassword = makeEncrypted("55e8fd90d8699a04b5f41b8e", "55e8fd90d8699a04b5f41b8e");
        assertEquals(correctPassword, incorrectPassword);
    }

    @Test
    public void anyDifferenceInTheSaltValue_andItWillNotEqualTheOther() {
        EncryptedPassword correctPassword = makeEncrypted("55e8fd90d8699a04b5f41b8e", "55e8fd90d8699a04b5f41b8e");
        EncryptedPassword incorrectPassword = makeEncrypted("55e8fd90d8699a04b5f41b8d", "55e8fd90d8699a04b5f41b8e");
        assertNotEquals(correctPassword, incorrectPassword);
    }

    @Test
    public void anyDifferenceInTheHashValue_andItWillNotEqualTheOther() {
        EncryptedPassword correctPassword = makeEncrypted("55e8fd90d8699a04b5f41b8e", "55e8fd90d8699a04b5f41b8e");
        EncryptedPassword incorrectPassword = makeEncrypted("55e8fd90d8699a04b5f41b8e", "55e8fd90d8699a04b5f41b8a");
        assertNotEquals(correctPassword, incorrectPassword);
    }
}