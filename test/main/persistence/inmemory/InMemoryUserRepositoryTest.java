package main.persistence.inmemory;

import main.domain.account.UserRepository;
import main.domain.account.UserRepositoryTest;

public class InMemoryUserRepositoryTest extends UserRepositoryTest {
    protected UserRepository getRepository() {
        return new InMemoryUserRepository();
    }

    protected String getValidId() {
        return "1";
    }

    protected String getInvalidId() {
        return "";
    }
}