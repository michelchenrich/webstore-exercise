package main.persistence.account;

public class InMemoryUserRepositoryTest extends AbstractUserRepositoryTest {
    protected UserRepository makeRepository() {
        return new InMemoryUserRepository();
    }

    protected String getExampleId() {
        return "1";
    }
}