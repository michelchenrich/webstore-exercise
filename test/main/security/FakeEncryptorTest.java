package main.security;

import main.domain.account.Encryptor;

public class FakeEncryptorTest extends EncryptorTest {
    protected Encryptor makeEncryptor() {
        return new FakeEncryptor();
    }
}
