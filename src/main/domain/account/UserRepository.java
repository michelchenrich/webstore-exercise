package main.domain.account;

import main.domain.Repository;

public interface UserRepository extends Repository<User> {
    boolean hasWithEmail(Email email);
    User getByEmail(Email email);
}
