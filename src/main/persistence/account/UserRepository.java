package main.persistence.account;

import main.domain.account.Email;
import main.domain.account.User;
import main.persistence.Repository;

public interface UserRepository extends Repository<User> {
    boolean hasWithEmail(Email email);
    User getByEmail(Email email);
}
