package main.account;

public interface UserRepository {
    boolean hasWithEmail(Email email);
    User getByEmail(Email email);
    void save(User user);
    User getById(String id);
    boolean hasWithId(String id);
}
