package main.persistence.inmemory;

import main.domain.account.UserRepository;
import main.domain.account.UserRepositoryTest;

public class InMemoryUserRepositoryTest extends UserRepositoryTest {
    protected UserRepository makeRepository() {
        return new InMemoryUserRepository();
    }

    protected String getExampleId() {
        return "1";
    }
}