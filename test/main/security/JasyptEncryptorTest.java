package main.security;

import main.domain.account.Encryptor;

public class JasyptEncryptorTest extends EncryptorTest {
    protected Encryptor makeEncryptor() {
        return new JasyptEncryptor();
    }
}