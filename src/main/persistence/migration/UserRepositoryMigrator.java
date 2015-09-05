package main.persistence.migration;

import main.domain.account.Email;
import main.domain.account.User;
import main.domain.account.UserRepository;

public class UserRepositoryMigrator extends RepositoryMigrator<User> implements UserRepository {
    private final UserRepository current;

    public UserRepositoryMigrator(UserRepository old, UserRepository current) {
        super(old, current);
        this.current = current;
    }

    public boolean hasWithEmail(Email email) {
        ensureMigration();
        return current.hasWithEmail(email);
    }

    public User getByEmail(Email email) {
        ensureMigration();
        return current.getByEmail(email);
    }
}
