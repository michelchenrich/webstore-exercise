package main.persistence.account.inmemory;

import main.persistence.account.AbstractUserRepositoryTest;
import main.persistence.account.UserRepository;

public class InMemoryUserRepositoryTest extends AbstractUserRepositoryTest {
    protected UserRepository makeRepository() {
        return new InMemoryUserRepository();
    }

    protected String getExampleId() {
        return "1";
    }
}