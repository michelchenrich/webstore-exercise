package main.account;

public interface UserRepository extends Repository<User> {
    boolean hasWithEmail(Email email);
    User getByEmail(Email email);
}
